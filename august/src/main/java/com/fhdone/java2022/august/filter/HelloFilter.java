package com.fhdone.java2022.august.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class HelloFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("########## Initiating Custom filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, 
                         ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());

        //call next filter in the filter chain
        filterChain.doFilter(request, response);

        log.info("Logging Response :{}", response.getContentType());
    }

    @Override
    public void destroy() {

    }
}
