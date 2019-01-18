package cn.com.app.service.developer.impl;

import cn.com.app.dao.developer.AppInfoMapper;
import cn.com.app.pojo.*;
import cn.com.app.service.developer.AppInfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    AppInfoMapper appInfoMapper;
    public Map<String, Object> AppListData(String pageNum, AppInfo appInfo) {
        Map<String,Object> map = new HashMap<String, Object>();
        int pageNumber = pageNum!=null?Integer.parseInt(pageNum):1;
        int pageSize = 3;
        int totalRecord = appInfoMapper.totalRecord(appInfo);
        int totalPage = totalRecord%pageSize==0?totalRecord/pageSize:totalRecord/pageSize+1;
        int pageIndex = (pageNumber-1)*pageSize;
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("AppInfo",appInfo);
        List<AppInfo> appInfos = appInfoMapper.getAppList(map);
        for (AppInfo a:appInfos){
            System.out.println(a.toString()+"*******************************************测试!!!!!!!!!!!!!!");
        }
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("appInfos",appInfos);
        map1.put("pages",totalPage);
        map1.put("pageNum",pageNumber);
        map1.put("total",totalRecord);
        return map1;
    }

    public List<DataDictionary> appVersionList(String condition) {
        return appInfoMapper.appVersionStatusList(condition);
    }

    public List<DataDictionary> appFlatFormList(String condition) {
        return appInfoMapper.appFlatFormList(condition);
    }

    public List<AppCategory> appCategoryList(Long condition) {
        return appInfoMapper.appCategoryList(condition);
    }

    public List<AppCategory> appCategoryList2(Long parentId) {
        return appInfoMapper.appCategoryList2(parentId);
    }

    public List<AppCategory> appCategoryList3() {
        return appInfoMapper.appCategoryList3();
    }

    public int addAppInfo(AppInfo appInfo, MultipartFile appImage, String filePath) {
        File path = new File(filePath);
        //判断filePath路径是否真实存在
        if (path.exists()){
            //获取文件名称
            String imageName =appImage.getOriginalFilename();
            //获取文件大小
            long fileSize =appImage.getSize();
            if (imageName.toLowerCase().endsWith(".gif") ||
                    imageName.toLowerCase().endsWith(".bmp") ||
                    imageName.toLowerCase().endsWith(".jpeg") ||
                    imageName.toLowerCase().endsWith(".icon") ||
                    imageName.toLowerCase().endsWith(".jpg")){
                if (fileSize<1024*1024){
                    try {
                        //执行写入操作,
                        File newFile = new File(path+"/"+imageName);

                        appImage.transferTo(newFile);
                        //将路径放到数据库

                        int n = appInfoMapper.addAppInfo(appInfo);
                        if (n > 0) {
                            return 0;
                        }else {
                            //写入数据库失败!
                            return 1;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    //文件大小不符合!
                    return 2;
                }
            }
            else {
                //文件格式不符合!
                return 3;
            }
        }
        //文件路径不存在!
        return 4;
    }

    public List<ExtAppVersion> getAppVersionList(Long appId) {
        return appInfoMapper.getAppVersionList(appId);
    }

    public int addVersionInfo(AppVersion appVersion, MultipartFile apkFile, String filePath,String id,String appId) {
        File path = new File(filePath);
        //判断filePath路径是否真实存在
        if (path.exists()){
            //获取文件名称
            String apkName = apkFile.getOriginalFilename();
            //获取文件大小
            long fileSize = apkFile.getSize();
            if (apkName.toLowerCase().endsWith(".apk")){
                if (fileSize<1024*1024){
                    try {
                        //执行写入操作
                        File newFile = new File(path+"/"+apkName);
                        apkFile.transferTo(newFile);
                        //将路径放到数据库

                        int n = appInfoMapper.addVersionInfo(appVersion);
                        int i = appInfoMapper.updateVersionId(Long.parseLong(id),Long.parseLong(appId));
                        if (n > 0) {
                            return 0;
                        }else {
                            //写入数据库失败!
                            return 1;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    //文件大小不符合要求
                    return 2;
                }
            }else {
                //文件格式不符合!
                return 3;
            }
        }
        //文件路径不存在!
        return 4;
    }

    public AppInfo getAppById(Long id) {
        return appInfoMapper.getAppById(id);
    }

    public int modifyAppInfo(AppInfo appInfo,MultipartFile appImage,String filePath) {
        File path = new File(filePath);
        //判断filePath路径是否真实存在
        if (path.exists()){
            //获取文件名称
            String imageName = appImage.getOriginalFilename();
            //获取文件大小
            long fileSize = appImage.getSize();
            if (imageName.toLowerCase().endsWith(".gif") ||
                    imageName.toLowerCase().endsWith(".bmp") ||
                    imageName.toLowerCase().endsWith(".jpeg") ||
                    imageName.toLowerCase().endsWith(".icon") ||
                    imageName.toLowerCase().endsWith(".png") ||
                    imageName.toLowerCase().endsWith(".jpg")){
                if (fileSize<1024*1024){
                    try {
                        //执行写入操作
                        File newFile = new File(path+imageName);
                        String imgLocPath = "/myProjectAssets/uploadfiles/"+imageName;
                        appImage.transferTo(newFile);
                        //将路径放到数据库
                        appInfo.setLogoPicPath(imgLocPath);
                        int n = appInfoMapper.modifyAppInfo(appInfo);
                        if (n > 0) {
                            return 0;
                        }else {
                            //写入数据库失败!
                            return 1;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    //文件大小不符合要求
                    return 2;
                }
            }else {
                //文件格式不符合!
                return 3;
            }
        }
        //文件路径不存在!
        return 4;
    }

    public AppVersion getAppVersionById(Long id) {
        return appInfoMapper.getAppVersionById(id);
    }

    public int appVersionModify(AppVersion appVersion, MultipartFile apkFile, String filePath) {
        File path = new File(filePath);
        if (path.exists()){
            String apkName = apkFile.getOriginalFilename();
            long fileSize = apkFile.getSize();
            if (apkName.toLowerCase().endsWith("apk")){
                if (fileSize<1024*10024*500){
                    File newFile =new File(path+apkName);
                    String apkLocPath = "/myProjectAssets/uploadfiles/"+apkName;
                    try {
                        apkFile.transferTo(newFile);
                        //将路径放到数据库
                        appVersion.setApkLocPath(apkLocPath);
                        appVersion.setApkFileName(apkName);
                        int n = appInfoMapper.appVersionModify(appVersion);
                        if (n > 0) {
                            return 0;
                        }else {
                            //写入数据库失败!!!
                            return 1;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    return 2;
                }
            }else {
                return 3;
            }
        }
        return 4;
    }

    public AppInfo getExtAppInfoById(Long id) {
        return appInfoMapper.getExtAppInfoById(id);
    }

    public int onsaleCheck(Long id) {
        int n = 0;
        AppInfo appInfo = appInfoMapper.getAppById(id);
        Long status = appInfo.getStatus();
        if (status == 2 || status == 5){
            n =  appInfoMapper.onsaleCheck(id,status);
            appInfo.setOnSaleDate(new Date());
            appInfo.setModifyDate(new Date());
        }else if (status == 4){
            n = appInfoMapper.onsaleCheck(id,status);
            appInfo.setOffSaleDate(new Date());
            appInfo.setModifyDate(new Date());
        }
        return n;
}

    public int deleteVersionById(Long id) {
        return appInfoMapper.deleteVersionById(id);
    }

    public int deleteAppInfoById(Long id) {
        return appInfoMapper.deleteAppInfoById(id);
    }


}
