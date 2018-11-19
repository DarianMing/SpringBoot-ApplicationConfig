package com.lm.demo.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DruidConfigurationOne {

    @Bean
    public DruidDataSource druidDataSource1 () throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        //jdbc配置
        druidDataSource.close();
        druidDataSource.setName("testDruidDataSource2");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("245220");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/st_mybatis?allowMultiQueries=true&autoReconnect=true&characterEncoding=utf-8");
        //连接池配置
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(3);
        druidDataSource.setMaxActive(20);
        druidDataSource.setMaxWait(20000);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(5);
        druidDataSource.setMaxOpenPreparedStatements(100);
        druidDataSource.setQueryTimeout(100);
        druidDataSource.setLoginTimeout(100);
        druidDataSource.setFilters("wall,slf4j");
        return druidDataSource;
    }
}
