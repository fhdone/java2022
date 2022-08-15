package com.fhdone.java2022.may;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class MayClientApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MayClientApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "5001"));
		app.run(args);
	}

}
