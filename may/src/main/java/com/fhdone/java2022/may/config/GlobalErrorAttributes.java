package com.fhdone.java2022.may.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

// https://blog.cnscud.com/springcloud/2021/08/09/springcloud-exceptionhandler.html
@Component
@Slf4j
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, 
                                                  ErrorAttributeOptions options) {
        
        Throwable error = super.getError(request);
        
        MergedAnnotation<ResponseStatus> responseStatusAnnotation = MergedAnnotations
                .from(error.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(ResponseStatus.class);
        HttpStatus errorStatus = determineHttpStatus(error, responseStatusAnnotation);
        log.info("errorStatus value:{}" ,errorStatus.value());
        
        Map<String, Object> map = super.getErrorAttributes(request, options);
        map.put("errorStatus", errorStatus);
        map.put("message", error.getMessage());
        return map;
    }
    
    private HttpStatus determineHttpStatus(Throwable error, 
                                           MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        
        if (error instanceof ResponseStatusException) {
           // return ((ResponseStatusException) error).getStatus();
        }
        return responseStatusAnnotation.getValue("code", HttpStatus.class).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
