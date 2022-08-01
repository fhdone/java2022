package com.fhdone.java2022.july;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(excludeFilters  = {@ComponentScan.Filter(
//    type = FilterType.ASSIGNABLE_TYPE, classes = {ServiceRegistryApplication.class})})
//@MapperScan(basePackages = {"com.fhdone.java2022.july.mapper"}, annotationClass = Mapper.class)
public class JulyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JulyApplication.class, args);
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