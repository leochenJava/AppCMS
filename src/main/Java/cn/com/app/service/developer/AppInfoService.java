package cn.com.app.service.developer;

import cn.com.app.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface AppInfoService {
    Map<String,Object> AppListData(String pageNumber, AppInfo appInfo);
    List<DataDictionary> appVersionList(String condition);
    List<DataDictionary> appFlatFormList(String condition);
    List<AppCategory> appCategoryList(Long condition);
    List<AppCategory> appCategoryList2(Long parentId);
    List<AppCategory> appCategoryList3();
    int addAppInfo(AppInfo appInfo, MultipartFile appFile,String filePath);
    List<ExtAppVersion> getAppVersionList(Long id);
    int addVersionInfo(AppVersion appVersion,MultipartFile apkFile,String filePath,String id,String appId);
    AppInfo getAppById(Long id);
    int modifyAppInfo(AppInfo appInfo,MultipartFile appImage,String filePath);
    AppVersion getAppVersionById(@Param("id")Long id);
    int appVersionModify(AppVersion appVersion,MultipartFile apkFile,String filePath);
    AppInfo getExtAppInfoById(Long id);
    int onsaleCheck(Long id);
    int deleteVersionById(Long id);
    int deleteAppInfoById(Long id);
}
