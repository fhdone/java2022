package com.fhdone.java2022.august.config;

import com.fhdone.java2022.august.utils.DemoMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class DemoWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private DemoMethodArgumentResolver demoMethodArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(demoMethodArgumentResolver);
    }
}
