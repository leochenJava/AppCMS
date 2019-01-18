package cn.com.app.service.backend.impl;

import cn.com.app.dao.backend.BackendLoginMapper;
import cn.com.app.pojo.BackendUser;
import cn.com.app.service.backend.BackendLofinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BackendLoginServiceImpl implements BackendLofinService {
    @Resource
    BackendLoginMapper backendLoginMapper;
    public BackendUser backendUserLogin(BackendUser backendUser) {
        return backendLoginMapper.backendUserLogin(backendUser);
    }
}
