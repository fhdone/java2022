package com.fhdone.java2022.july.config.animal;

import org.springframework.context.annotation.Bean;

public class AnimalConfig {

    @Bean
    public Dog getDog() {
        return new Dog();
    }

    @Bean
    public Cat getCat() {
        return new Cat();
    }
}

