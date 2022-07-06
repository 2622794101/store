package com.lgm.store.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 判断用户是否登录，通过检测session是否有uid判断
     *
     * @param request
     * @param response
     * @param handler
     * @return 返回值为ture继续执行，为false则不执行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object uid = session.getAttribute("uid");
        if (uid == null) {
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
