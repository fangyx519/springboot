package com.fyx.springboot.config;

import com.fyx.springboot.config.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Author fyx
 * @Time in 15:06 2020/5/24
 * @Despriction
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login_1.jsp","register_1.jsp","/css/*","/js/*","/img/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
