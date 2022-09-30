package com.project.config;

import com.project.aop.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-09-30-11:41
 * @version:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private UserLoginInterceptor userLoginInterceptor;

    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")//开放登录路径
                .excludePathPatterns("/user/register");
    }

}
