package com.example.springbootredisdemo03.config;

import com.example.springbootredisdemo03.interceptor.LoginInterceptor;
import com.example.springbootredisdemo03.interceptor.ResourcesInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: qyq
 * @Date: 2022/09/12/18:31
 * @Description:
 */
@Configuration
public class UniformWebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private ResourcesInterceptor resourcesInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        放行
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login").excludePathPatterns("/**/templates/**").excludePathPatterns("/user/main").excludePathPatterns("/user/loginService");
         registry.addInterceptor(resourcesInterceptor).addPathPatterns("/**");


    }
}
