package com.fhdone.java2022.june;


import com.fhdone.java2022.june.config.LoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class JuneClientApplication {
    public static void main(String[] args) {
		SpringApplication.run(JuneClientApplication.class, args);
	}

}
