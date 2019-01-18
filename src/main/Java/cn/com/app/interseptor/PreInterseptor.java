package cn.com.app.interseptor;

import cn.com.app.pojo.BackendUser;
import cn.com.app.pojo.DevUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreInterseptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        /**
         * 方法执行之前的一些业务判断
         *
         */

        String requestPath = request.getRequestURI();
        System.out.println(requestPath);
        if (requestPath.contains("login")){
            return true;
        }

        Object devUser =  request.getSession().getAttribute("devUserSession");
        Object backendUser =  request.getSession().getAttribute("userSession");
        System.out.println(backendUser);
        if ((devUser!=null && !"".equals(devUser) ) || (backendUser != null && !"".equals(backendUser))){
            System.out.println("放行ogin路径");
            return true;
        }else{
            System.out.println("拦截登陆!!!");
            response.sendRedirect(request.getContextPath()+"/403.jsp");
        }
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
