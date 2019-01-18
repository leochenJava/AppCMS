package cn.com.app.controller.developer;

import cn.com.app.pojo.*;
import cn.com.app.service.developer.AppInfoService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/appController")
public class AppInfoController {
    @Resource
    AppInfoService appInfoService;
    @RequestMapping(value = "/getAppList")
    public String getAppList(String pageIndex, AppInfo appInfo, HttpServletRequest request){
        Map<String,Object> map = appInfoService.AppListData(pageIndex,appInfo);
        request.setAttribute("pageInfo",map);//级联查询的数据

        List<DataDictionary> appVersionList = appInfoService.appVersionList("APP_STATUS");//APP状态选择框的5个状态
        Long queryStatus = appInfo.getStatus()!=null?appInfo.getStatus():0;
        request.setAttribute("queryStatus",queryStatus);//APP状态选中值
        request.setAttribute("statusList",appVersionList);

        List<DataDictionary> appFlatFormList = appInfoService.appFlatFormList("APP_FLATFORM");//查询框所属平台数据
        Long queryFlatformId = appInfo.getFlatformId()!=null?appInfo.getFlatformId():0;
        request.setAttribute("queryFlatformId",queryFlatformId);//所属平台选中值
        request.setAttribute("flatFormList",appFlatFormList);

        //一级分类
        List<AppCategory> appCategories = appInfoService.appCategoryList(null);
        Long queryCategoryLevel1 = appInfo.getCategoryLevel1()!=null?appInfo.getCategoryLevel1():0;
        request.setAttribute("queryCategoryLevel1",queryCategoryLevel1);
        request.setAttribute("categoryLevel1List",appCategories);
        //二级分类
        List<AppCategory> appCategories2 = appInfoService.appCategoryList2(Long.parseLong("1"));
        Long queryCategoryLevel2 = appInfo.getCategoryLevel2()!=null?appInfo.getCategoryLevel2():0;
        request.setAttribute("queryCategoryLevel2",queryCategoryLevel2);
        request.setAttribute("categoryLevel2List",appCategories2);

        //三级分类
        List<AppCategory> appCategories3 = appInfoService.appCategoryList3();
        Long queryCategoryLevel3 = appInfo.getCategoryLevel3()!=null?appInfo.getCategoryLevel3():0;
        request.setAttribute("categoryLevel3List",appCategories3);
        request.setAttribute("queryCategoryLevel3",queryCategoryLevel3);
        return "developer/appinfolist";
    }

    @RequestMapping(value = "/categoryJson",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getJsonCategory(String parentId){
        List<AppCategory> appCategories = appInfoService.appCategoryList(Long.parseLong(parentId));
        return JSONArray.toJSONString(appCategories);
    }

    @RequestMapping(value = "/add")
    public String addAppInfo(HttpServletRequest request){
        List<DataDictionary> appFlatFormList = appInfoService.appFlatFormList("APP_FLATFORM");
        request.setAttribute("flatFormList",appFlatFormList);

        List<AppCategory> appCategories = appInfoService.appCategoryList(null);
        request.setAttribute("categoryLevel1List",appCategories);
        return "developer/appinfoadd";
    }

    @RequestMapping(value = "/addInfo")
    public String addInfo(AppInfo appInfo, @RequestParam(value="a_logoPicPath",required= false)MultipartFile alogoPicPath, HttpServletRequest request){
        String filePath = request.getRealPath("/AppInfoSystem/myProjectAssets/uploadfiles");
        String fileName = alogoPicPath.getOriginalFilename();
        String logoPicPath = request.getContextPath()+"/AppInfoSystem/myProjectAssets/uploadfiles/"+fileName;
        String logoLocPath = filePath+ File.separator+fileName;
        appInfo.setLogoPicPath(logoPicPath);
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setCreationDate(new Date());
        appInfo.setCreatedBy(((DevUser)(request.getSession().getAttribute("devUserSession"))).getId());
        appInfo.setDevId(((DevUser)(request.getSession().getAttribute("devUserSession"))).getId());
        int n = appInfoService.addAppInfo(appInfo,alogoPicPath,filePath);
        if (n==1){
            request.setAttribute("error","写入数据库失败!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==2){
            request.setAttribute("error","文件的啊小不符合!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==3){
            request.setAttribute("error","文件格式不符合!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==4){
            request.setAttribute("error","文件路径不符合!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }
        return "developer/appinfolist";
    }

    @RequestMapping(value = "/appversionadd")
    public String addAppVersion(@RequestParam(value = "appInfoId")String appInfoId, HttpServletRequest request){
        //根据AppId增加版本(version表)
        //根据AppId查询出版本信息并且渲染在添加的jsp中
        List<ExtAppVersion> appVersionList = appInfoService.getAppVersionList(Long.parseLong(appInfoId));
        for (AppVersion appVersion:appVersionList){
            System.out.println("**********************************************测试APPID"+appVersion.toString());
        }
        //根据appInfo获取AppInfo对象,把对象发到add页面,那边要用这个id,添加版本信息的时候用来添加appId这个字段.
        AppInfo appInfo = appInfoService.getAppById(Long.parseLong(appInfoId));
        request.setAttribute("appInfo",appInfo);
        request.setAttribute("appVersion",appVersionList);
        return "developer/appversionadd";
    }

    @RequestMapping(value = "/appversionaddInfo")
    public String appVersionAddInfo(AppVersion appVersion,
                                    @RequestParam(value="a_downloadLink",required= false)MultipartFile a_downloadLink,
                                    HttpServletRequest request,String id,String appId){
        String filePath = request.getRealPath("/AppInfoSystem/myProjectAssets/uploadfiles");
        String fileName = a_downloadLink.getOriginalFilename();
        String downloadLink = request.getContextPath()+"/AppInfoSystem/myProjectAssets/uploadfiles/"+fileName;
        String apkLocPath = filePath+ File.separator+fileName;
        appVersion.setDownloadLink(downloadLink);
        appVersion.setApkFileName(fileName);
        appVersion.setApkLocPath(apkLocPath);
        appVersion.setCreatedBy(((DevUser)(request.getSession().getAttribute("devUserSession"))).getId());
        appVersion.setModifyBy(((DevUser)(request.getSession().getAttribute("devUserSession"))).getId());
        appVersion.setCreationDate(new Date());
        int n = appInfoService.addVersionInfo(appVersion,a_downloadLink,filePath,id,appId);
        if (n==1){
            request.setAttribute("error","写入数据库失败!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==2){
            request.setAttribute("error","文件的啊小不符合!!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==3){
            request.setAttribute("error","文件格式不符合!!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==4){
            request.setAttribute("error","文件路径不符合!!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }
        return "developer/appinfolist";
    }

    @RequestMapping(value = "/modifyVersion/{versionid}/{appinfoid}")//这里的两个参数是从js页面传过来的,形式参数可以随便写
    public String modifyVersion(@PathVariable String versionid,@PathVariable String appinfoid,HttpServletRequest request){//这里的形参要和上面的形参完全一致.
        //所属平台和一级分类
        List<DataDictionary> appFlatFormList = appInfoService.appFlatFormList("APP_FLATFORM");
        request.setAttribute("flatFormList",appFlatFormList);
        List<AppCategory> appCategories = appInfoService.appCategoryList(null);
        System.out.println("****分割线***");
        request.setAttribute("categoryLevel1List",appCategories);
        //根据appInfoId获取AppVersion对象的集合(因为修改页面进去以后要显示所有的历史版本,是个集合)
        List<ExtAppVersion> extAppVersionList = appInfoService.getAppVersionList(Long.parseLong(appinfoid));
        request.setAttribute("appVersionList",extAppVersionList);
        //根据versionId(appInfo里的versionId就是Version表里的主键)获取AppVersion对象
        AppVersion appVersion = appInfoService.getAppVersionById(Long.parseLong(versionid));
        request.setAttribute("appVersion",appVersion);
        return "developer/appversionmodify";
    }

    @RequestMapping(value = "/modify/{appinfoid}")
    public String modifyInfo(@PathVariable String appinfoid, HttpServletRequest request){
        //根据id查询ExtAppInfo对象
        AppInfo extAppInfo = appInfoService.getExtAppInfoById(Long.parseLong(appinfoid));
        request.setAttribute("appInfo",extAppInfo);
        //将所属平台和一级分类数据发送到页面,二级三级会根据jquey获取
        List<DataDictionary> appFlatFormList = appInfoService.appFlatFormList("APP_FLATFORM");
        request.setAttribute("flatFormList",appFlatFormList);

        List<AppCategory> appCategories = appInfoService.appCategoryList(null);
        request.setAttribute("categoryLevel1List",appCategories);

        return "developer/appinfomodify";
    }
    @RequestMapping(value = "/modifyInfo")
    public String modifyInfo(AppInfo appInfo,MultipartFile attach,HttpServletRequest request){
        String filePath = request.getRealPath("/myProjectAssets/images/");
        int n = appInfoService.modifyAppInfo(appInfo,attach,filePath);
        if (n==1){
            request.setAttribute("error","写入数据库失败!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==2){
            request.setAttribute("error","文件的啊小不符合!!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==3){
            request.setAttribute("error","文件格式不符合!!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==4){
            request.setAttribute("error","文件路径不符合!!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }
        return "developer/appinfolist";

    }

    @RequestMapping(value = "/modifyVersionInfo")
    public String appVersionModify(AppVersion appVersion,MultipartFile attach,HttpServletRequest request){
        String filePath = request.getRealPath("/myProjectAssets/images/");
        int n = appInfoService.appVersionModify(appVersion,attach,filePath);
        if (n==1){
            request.setAttribute("error","写入数据库失败!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==2){
            request.setAttribute("error","文件的啊小不符合!!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==3){
            request.setAttribute("error","文件格式不符合!!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }else if (n==4){
            request.setAttribute("error","文件路径不符合!!!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }
        return "developer/appinfolist";
    }

    @RequestMapping(value = "/appview/{appinfoid}")
    public String appView(@PathVariable String appinfoid, HttpServletRequest request){
        List<ExtAppVersion> appVersionList = appInfoService.getAppVersionList(Long.parseLong(appinfoid));
        request.setAttribute("appVersionList",appVersionList);
        //根据id查询ExtAppInfo对象
        AppInfo extAppInfo = appInfoService.getExtAppInfoById(Long.parseLong(appinfoid));
        request.setAttribute("appInfo",extAppInfo);
        return "developer/appinfoview";
    }


    @RequestMapping(value = "/sale/{appId}")
    @ResponseBody
    public String onsaleCheck(@PathVariable String appId,HttpServletRequest request){
        int n = appInfoService.onsaleCheck(Long.parseLong(appId));
        if (n == 0){
            return "{\"resultMsg\":\"failed\"}";
        }
        return "{\"resultMsg\":\"success\"}";
    }

    @RequestMapping(value = "/delAppInfo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteApp(@RequestParam("id") String id){
        int n = appInfoService.deleteAppInfoById(Long.parseLong(id));
        int i = appInfoService.deleteVersionById(Long.parseLong(id));
        if (n>0 && i>0){
            return "{\"delResult\":\"true\"}";
        }
        return "{\"delResult\":\"false\"}";
    }
}
