package com.sht.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Aaron
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器需放在最上面
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
    }

    /**
     * 跨域处理
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }

    /**
     * 静态资源映射配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/common/**").addResourceLocations("file:/common/");
    }
}
