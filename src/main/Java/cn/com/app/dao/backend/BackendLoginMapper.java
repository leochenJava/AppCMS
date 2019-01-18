package cn.com.app.dao.backend;

import cn.com.app.pojo.BackendUser;

public interface BackendLoginMapper {
    BackendUser backendUserLogin(BackendUser backendUser);
}
