package com.fhdone.java2022.august;


import org.springframework.boot.SpringApplication;

import java.util.Collections;

//@SpringBootApplication
//@ComponentScan(excludeFilters  = {@ComponentScan.Filter(
//    type = FilterType.ASSIGNABLE_TYPE, classes = {ServiceRegistryApplication.class})})
//@MapperScan(basePackages = {"com.fhdone.java2022.august.mapper"}, annotationClass = Mapper.class)
public class AugustApplication2 {

    public static void main(String[] args) {
        //        SpringApplication.run(augustApplication.class, args);
        SpringApplication app = new SpringApplication(AugustApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8002"));
        app.run(args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }

}