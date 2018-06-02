package com.chd.hao.manager.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import javax.sql.DataSource;

/**
 * Created by zhanghao68 on 2018/4/18
 */

@Configuration
public class DbConfig {


    @Bean(name = "datasource")
    public DataSource buildDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        // 基本属性 url、user、password
        dataSource.setUrl("jdbc:mysql://localhost:3306/bishe?characterEncoding=UTF8");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        dataSource.setTimeBetweenLogStatsMillis(60000);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory buildSqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mapper/mybatis-config.xml"));
        sqlSessionFactoryBean.setDataSource(buildDataSource());

        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate buildSqlSessionTemplate() {
        return new SqlSessionTemplate(buildSqlSessionFactory());
    }

}
