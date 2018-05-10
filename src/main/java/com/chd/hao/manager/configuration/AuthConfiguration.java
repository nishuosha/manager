package com.chd.hao.manager.configuration;

import com.chd.hao.manager.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhanghao68 on 2018/5/7
 */
@Configuration
public class AuthConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public AuthInterceptor interceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(interceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/frame/*", "/login/*", "/admin/add",
                        "/admin/getByName", "/admin/getById",
                        "/user/add", "/user/getByName", "/user/getById");

        super.addInterceptors(registry);
    }
}
