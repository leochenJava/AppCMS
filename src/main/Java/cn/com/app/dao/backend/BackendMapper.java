package cn.com.app.dao.backend;

import cn.com.app.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BackendMapper {
    int chechAppById(@Param("id") Long id, @Param("status") Long status);//审核App
}
