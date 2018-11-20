package com.lm.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DruidConfigurationOne {

    @Bean
    public DruidDataSource druidDataSource1 () throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        //数据库配置
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
        druidDataSource.setFilters("wall,slf4j,stat");
        return druidDataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet());
        Set<String> set = new HashSet<>();
        set.add("/druid/*");
        servletRegistrationBean.setUrlMappings(set);
//        servletRegistrationBean.addInitParameter("loginUsername" , "admin");
//        servletRegistrationBean.addInitParameter("loginPassword" , "123456");
        return servletRegistrationBean;
    }
}
