package com.lm.demo.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
   改配置的存在是为了定义的三个Druid连接池能够同时存在
   查看application单独配置的效果，需要将这三个配置类都注释掉
 */
@Configuration
public class DruidConfigurationThree {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource2 () {
        return new DruidDataSource();
    }
}
