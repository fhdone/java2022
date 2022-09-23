package com.fhdone.java2022.june;

import com.fhdone.java2022.june.config.LoadBalancerConfig;
import com.fhdone.java2022.march.dto.demo.Contract;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient("august-eureka-service")
@LoadBalancerClient(name = "august-eureka-service", configuration = LoadBalancerConfig.class)
public interface AugustClient {

  @GetMapping("/test")
  public String july();

  @GetMapping("/contract/queryContact")
  public List<Contract> queryContact();

}