package cn.com.app.controller.backend;

import cn.com.app.pojo.AppCategory;
import cn.com.app.pojo.AppInfo;
import cn.com.app.pojo.AppVersion;
import cn.com.app.pojo.DataDictionary;
import cn.com.app.service.backend.BackendControllerService;
import cn.com.app.service.developer.AppInfoService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/appCheckController")
public class BackendController {
    @Resource
    AppInfoService appInfoService;
    @Resource
    BackendControllerService backendControllerService;
    @RequestMapping(value = "/applist")
    public String getAppList(String pageIndex, AppInfo appInfo, HttpServletRequest request){
        Map<String,Object> map = appInfoService.AppListData(pageIndex,appInfo);
        request.setAttribute("appInfoList",map);//级联查询的数据

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
        return "backend/applist";
    }

    @RequestMapping(value = "categorylevellist",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getJsonCategory(String integer){
        List<AppCategory> appCategories = appInfoService.appCategoryList(Long.parseLong(integer));
        return JSONArray.toJSONString(appCategories);
    }

    @RequestMapping(value = "/appcheck")
    public String appcheck(String aid,String vid,HttpServletRequest request){
        AppInfo extAppInfo = appInfoService.getExtAppInfoById(Long.parseLong(aid));
        request.setAttribute("ExtAppInfo",extAppInfo);
        System.out.println("*****************");
        AppVersion appVersion = appInfoService.getAppVersionById(Long.parseLong(vid));
        request.setAttribute("ExtAppVersion",appVersion);
        return "backend/appcheck";
    }

    @RequestMapping(value = "/checksave")
    public String checksave(String id,String status,HttpServletRequest request){
        int n = backendControllerService.chechAppById(Long.parseLong(id),Long.parseLong(status));
        if (n==0){
            request.setAttribute("error","审核失败!");
            return "backend/appcheck";
        }
        return "backend/applist";
    }
}
