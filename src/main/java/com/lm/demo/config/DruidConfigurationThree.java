package com.lm.demo.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallFilter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Configuration
public class DruidConfigurationThree {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource2 () {
        DruidDataSource dataSource = new DruidDataSource();
        List<Filter> filters = new ArrayList<>();
        StatFilter statFilter = new StatFilter();
        //开启慢SQL监控
        statFilter.setLogSlowSql(true);
        //配置SQL慢的标准
        statFilter.setSlowSqlMillis(2);
        //SQL合并配置 方式一
        statFilter.setMergeSql(true);
        //SQL合并配置 方式二
//        MergeStatFilter mergeStatFilter = new MergeStatFilter();
//        filters.add(mergeStatFilter);
        //SQL合并配置 方式三
//        Properties properties = new Properties();
//        properties.setProperty("druid.stat.mergeSql" , "true");
//        dataSource.setConnectProperties(properties);
        //SQL合并配置 方式四 增加JVM的参数配置:-Ddruid.stat.mergeSql=true
        filters.add(statFilter);
        //配置SQL防火墙
        WallFilter wallFilter = new WallFilter();
        //有时候，一些应用框架做了自己的JDBC Proxy Driver，导致DruidDataSource无法正确识别数据库的类型，则需要特别指定
        wallFilter.setDbType("mysql");
        filters.add(wallFilter);
        Slf4jLogFilter slf4jLogFilter = new Slf4jLogFilter();
        slf4jLogFilter.setStatementExecutableSqlLogEnable(true);
        slf4jLogFilter.setDataSourceLogEnabled(true);
        filters.add(slf4jLogFilter);
        dataSource.setProxyFilters(filters);
//        dataSource.getProxyFilters().set(0,statFilter);
        //连接泄漏监测
        //当程序存在缺陷时，申请的连接忘记关闭，这时候，就存在连接泄漏了。Druid提供了RemoveAbandanded相关配置，用来关闭长时间不使用的连接。
        //配置removeAbandoned对性能会有一些影响，建议怀疑存在泄漏之后再打开。在上面的配置中，如果连接超过30分钟未关闭，就会被强行回收，并且日志记录连接申请时的调用堆栈。
        //当removeAbandoned=true之后，可以在内置监控界面datasource.html中的查看ActiveConnection StackTrace属性的，可以看到未关闭连接的具体堆栈信息，从而方便查出哪些连接泄漏了。
        //打开removeAbandoned功能
        dataSource.setRemoveAbandoned(true);
        //1800秒，也就是30分钟
        dataSource.setRemoveAbandonedTimeout(10);
        //关闭abanded连接时输出错误日志
        dataSource.setLogAbandoned(true);
        return dataSource;
    }
}
