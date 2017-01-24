package com.tqmars.cardrecycle.webapi;

import com.tqmars.cardrecycle.webapi.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jjh on 1/20/17.
 */
@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).
                excludePathPatterns("/user/login").
                excludePathPatterns("/user/register").
                excludePathPatterns("/user/getVCode").
                excludePathPatterns("/user/getSms");
        super.addInterceptors(registry);
    }
}
