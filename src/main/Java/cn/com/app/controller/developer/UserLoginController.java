package cn.com.app.controller.developer;

import cn.com.app.pojo.DevUser;
import cn.com.app.service.developer.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserLoginController {
    @Resource
    UserLoginService userLoginService;
    @RequestMapping(value = "/devLoginController/login")
    public String userLogin(){
        System.out.println("#############################################################");
        return "devlogin";
    }

    @RequestMapping(value = "/devLoginController/devLogin")
    public String userLoginCheck(DevUser devUser, HttpServletRequest request){
        DevUser user = userLoginService.devUserLogin(devUser);
        if (user == null){
            request.setAttribute("error","用户名或者密码错误!");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/index.jsp";
        }
        request.getSession().setAttribute("devUserSession",user);
        return "developer/main";
    }
    @RequestMapping(value = "/devLoginController/logout")
    public String devLogOut(HttpServletRequest request){
        request.getSession().removeAttribute("devUserSession");
        return "devlogin";
    }
}
