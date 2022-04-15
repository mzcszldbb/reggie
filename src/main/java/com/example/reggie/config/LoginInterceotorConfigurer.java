package com.example.reggie.config;

import com.example.reggie.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceotorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList<String>();
        patterns.add("/employee/login");
        patterns.add("/employee/logout");
        patterns.add("/backend/page/login/login.html");
        patterns.add("/backend/api/**");
        patterns.add("/backend/images/**");
        patterns.add("/backend/js/**");
        patterns.add("/backend/plugins/**");
        patterns.add("/backend/styles/**");
        patterns.add("/backend/favicon.ico");
        patterns.add("/front/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
