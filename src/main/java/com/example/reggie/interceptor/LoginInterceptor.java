package com.example.reggie.interceptor;

import com.example.reggie.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object employee =  session.getAttribute("employee");
        String requestURI = request.getRequestURI();// /backend/index.html
        log.info("拦截到请求：{}",requestURI);
        if (employee == null){
            response.sendRedirect("/backend/page/login/login.html");
            return false;
        }
        return true;
    }
}
