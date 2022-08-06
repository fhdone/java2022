package com.fhdone.java2022.june;

import com.fhdone.java2022.june.config.LoadBalancerConfig;
import com.fhdone.java2022.june.dto.Contract;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient("july-eureka-service")
@LoadBalancerClient(name = "july-eureka-service", configuration = LoadBalancerConfig.class)
public interface JuneClient {

  @GetMapping("/test")
  public String july();

  @GetMapping("/contract/queryContact")
  public List<Contract> queryContact();

}