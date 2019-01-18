package cn.com.app.service.backend.impl;

import cn.com.app.dao.backend.BackendMapper;
import cn.com.app.service.backend.BackendControllerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BackendControllerServiceImpl implements BackendControllerService {
    @Resource
    BackendMapper backendMapper;
    public int chechAppById(Long id,Long status) {
        return backendMapper.chechAppById(id,status);
    }
}
