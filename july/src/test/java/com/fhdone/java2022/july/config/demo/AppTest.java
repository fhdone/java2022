package com.fhdone.java2022.july.config.demo;

import com.fhdone.java2022.july.BaseTest;
import com.fhdone.java2022.july.config.animal.AnimalConfig;
import com.fhdone.java2022.july.config.animal.Cat;
import com.fhdone.java2022.july.config.animal.Dog;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;


//https://blog.csdn.net/pange1991/article/details/81356594
//@SpringBootApplication(scanBasePackageClasses = AppTest.class)
//@ComponentScan
/*把用到的资源导入到当前容器中*/
//@Import({Dog.class, Cat.class})
@Import(AnimalConfig.class)
public class AppTest extends BaseTest {

    @Autowired
    public ApplicationContext applicationContext;

    @Test
    public void test() throws Exception {

        //ConfigurableApplicationContext context = SpringApplication.run(AppTest.class, args);
        System.out.println(applicationContext.getBean(Dog.class));
        System.out.println(applicationContext.getBean(Cat.class));
//        context.close();
    }
}

