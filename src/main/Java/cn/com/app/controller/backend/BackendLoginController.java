package cn.com.app.controller.backend;

import cn.com.app.pojo.BackendUser;
import cn.com.app.service.backend.BackendLofinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/userLoginController")
public class BackendLoginController {
    @Resource
    BackendLofinService backendLofinService;
    @RequestMapping(value = "/login")
    public String userLogin(){
        return "backendlogin";
    }
    @RequestMapping(value = "/userlogin")
    public String userLoginCheck(BackendUser backendUser, HttpServletRequest request){
        BackendUser backendUser1 = backendLofinService.backendUserLogin(backendUser);
        if (backendUser1 == null){
            request.setAttribute("error","用户名或者密码错误!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/index.jsp";
        }
        request.setAttribute("userSession",backendUser1);
        return "backend/main";
    }
}
