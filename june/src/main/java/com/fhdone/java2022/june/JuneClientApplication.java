package com.fhdone.java2022.june;


import com.fhdone.java2022.june.config.LoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableHystrix
public class JuneClientApplication {
    public static void main(String[] args) {
		SpringApplication.run(JuneClientApplication.class, args);
	}

}
