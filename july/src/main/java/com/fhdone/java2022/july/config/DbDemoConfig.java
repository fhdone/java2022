package com.fhdone.java2022.july.config;

import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.fhdone.java2022.july.mapper.demo"}, sqlSessionFactoryRef = "sqlSessionFactoryDbDemo")
public class DbDemoConfig {

    //@Autowired
    @Resource(name="dbDemo")
    private DataSource dataSourceDbDemo;


    @Bean
    public SqlSessionFactory sqlSessionFactoryDbDemo() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceDbDemo);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/demo/*.xml"));
//        factoryBean.setTypeAliasesPackage("com.fhdone.java2022.march.dto.demo");
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateDbDemo() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryDbDemo());
    }

}