package com.project.config;

import com.project.aop.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //配置允许跨域的路径
        registry.addMapping("/**")
                //配置允许访问的跨域资源的请求域名
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                //配置允许访问该跨域资源服务器的请求方法
                .allowedMethods("*")
                //配置允许请求 头部head的访问
                .allowedHeaders("*");
    }
}
