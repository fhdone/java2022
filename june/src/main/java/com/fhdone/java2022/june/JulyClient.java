package com.fhdone.java2022.june;

import com.fhdone.java2022.march.dto.demo.Contract;
import com.fhdone.java2022.june.config.LoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
@FeignClient("july-eureka-service")
@LoadBalancerClient(name = "july-eureka-service", configuration = LoadBalancerConfig.class)
public interface JulyClient {

  @GetMapping("/test")
  public String july();

  @GetMapping("/contract/queryContact")
  public List<Contract> queryContact();
  
  @PostMapping("/contract/queryContactPage")
  public List<Contract> queryContactPage(@RequestBody Map<String, Object> requestMap);

}