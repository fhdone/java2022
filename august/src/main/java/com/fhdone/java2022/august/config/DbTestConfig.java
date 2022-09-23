package com.fhdone.java2022.august.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.fhdone.java2022.august.mapper.test"}, sqlSessionFactoryRef = "sqlSessionFactoryDbTest")
public class DbTestConfig {

    //@Autowired
    //@Qualifier("dbTest")
    @Resource(name="dbTest")
    private DataSource dataSourceDbTest;


    @Bean
    public SqlSessionFactory sqlSessionFactoryDbTest() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceDbTest);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test/*.xml"));
//        factoryBean.setTypeAliasesPackage("com.fhdone.java2022.march.dto.test");
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateDbTest() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryDbTest());
    }

}