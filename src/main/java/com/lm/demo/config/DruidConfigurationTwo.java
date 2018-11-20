package com.lm.demo.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Configuration
public class DruidConfigurationTwo {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "db")
    public DataSource druidDataSource () {
        DruidDataSource druidDataSource = new DruidDataSource();
        List<Filter> filters = new ArrayList<>();
        StatFilter statFilter = new StatFilter();
        //开启慢SQL监控
        statFilter.setLogSlowSql(true);
        //配置SQL慢的标准
        statFilter.setSlowSqlMillis(2);
        filters.add(statFilter);
        druidDataSource.setProxyFilters(filters);
        return druidDataSource;
    }

//    @Bean
//    public ServletRegistrationBean druidServlet() {
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet());
//        Set<String> set = new HashSet<>();
//        set.add("/druid/*");
//        servletRegistrationBean.setUrlMappings(set);
////        servletRegistrationBean.addInitParameter("loginUsername" , "admin");
////        servletRegistrationBean.addInitParameter("loginPassword" , "123456");
//        return servletRegistrationBean;
//    }
}
