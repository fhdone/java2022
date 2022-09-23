package com.fhdone.java2022.august.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * https://www.jianshu.com/p/08cc28921fd0
 * https://segmentfault.com/a/1190000040346944
 */
@Configuration
public class WebSecurityConfig {  //  extends WebSecurityConfigurerAdapter {


    @Bean
//    @Order(2)
    public SecurityFilterChain webSiteSecurityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        // 创建用户
        authenticationManagerBuilder.inMemoryAuthentication()
            .withUser("admin")
            .password(new BCryptPasswordEncoder().encode("admin"))
            .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"))
            .and()
            .withUser("dev")
            .password(new BCryptPasswordEncoder().encode("dev"))
            .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DEV"))
            .and()
            .passwordEncoder(new BCryptPasswordEncoder());

        // 只处理 所有 开头的请求
        return http.antMatcher("/**")
            .authorizeRequests()
            // 所有请求都必须要认证才可以访问
            .anyRequest() //.authenticated()
            .hasRole("ADMIN")
            .and()
            // 禁用csrf
            .csrf().disable()
            // 启用表单登录
            .formLogin().permitAll()
            .and()
            .build();
    }

    /**
     * 忽略静态资源
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer( ){
        return web -> web.ignoring()
            .antMatchers("/**/**")
            .antMatchers("/**/js/**")
            .antMatchers("/**/css/**");

    }
    

}
