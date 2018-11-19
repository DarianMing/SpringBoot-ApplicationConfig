package com.lm.demo.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DruidConfigurationTwo {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "db")
    public DataSource druidDataSource (){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setLoginTimeout(100);
        druidDataSource.setQueryTimeout(100);
        return druidDataSource;
    }
}
