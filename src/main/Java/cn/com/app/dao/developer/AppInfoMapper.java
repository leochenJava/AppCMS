package cn.com.app.dao.developer;

import cn.com.app.pojo.*;
import org.apache.ibatis.annotations.Param;
import sun.misc.Version;

import java.util.List;
import java.util.Map;

public interface AppInfoMapper {
    List<AppInfo>getAppList(Map<String,Object> map);//根据条件查询App信息数据集合
    int totalRecord(AppInfo appInfo);//根据条件查询app记录数
    List<DataDictionary> appVersionStatusList(String condition);//根据条件查询数据字典中的状态信息
    List<DataDictionary> appFlatFormList(String condition);//根据条件查询数据字典中的所属平台
    List<AppCategory> appCategoryList( @Param("parentId") Long parentId);//查询一级分类数据
    List<AppCategory> appCategoryList2(Long parentId);//查询二级分类数据
    List<AppCategory> appCategoryList3();//查询三级分类数据
    int addAppInfo(AppInfo appInfo);//添加AppInfo
    List<ExtAppVersion> getAppVersionList(Long appId);//根据AppId查询版本信息对象
    int addVersionInfo(AppVersion appVersion);//添加版本信息
    AppInfo getAppById(Long id);//根据id查询app对象
    int modifyAppInfo(AppInfo appInfo);//修改AppInfo基础信息
    AppVersion getAppVersionById(@Param("id")Long id);//根据id获取AppVersion对象
    int appVersionModify(AppVersion appVersion);//修改AppVersion信息
    AppInfo getExtAppInfoById(@Param("id")Long id);//根据id查找ExtAppInfo对象,跟第一条类似,第一条返回集合,这个返回单对象
    int updateVersionId(@Param("id") Long id,@Param("appId")Long appId);//在添加新版本的同时也要在主表上把versionId也添加进去.
    int onsaleCheck(@Param("id")Long id,@Param("status")Long status);//上架下架
    int deleteVersionById(@Param("appId")Long id);//根据appId删除该Id下的所有版本信息对象
    int deleteAppInfoById(@Param("id")Long id);//根据id删除AppInfo信息对象
}
