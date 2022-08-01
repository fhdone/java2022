package com.fhdone.java2022.june;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
//@Component
@FeignClient("JULY-EUREKA-SERVICE")
public interface JuneClient {

  @GetMapping("/test")
  public String july();



}