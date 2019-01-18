package cn.com.app.service.developer.impl;

import cn.com.app.dao.developer.DevLoginMapper;
import cn.com.app.pojo.DevUser;
import cn.com.app.service.developer.UserLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    DevLoginMapper userLoginMapper;
    public DevUser devUserLogin(DevUser devUser) {
        return userLoginMapper.devUserLogin(devUser);
    }
}
