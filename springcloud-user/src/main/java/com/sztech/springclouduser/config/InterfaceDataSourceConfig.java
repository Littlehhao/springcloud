package com.sztech.springclouduser.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.sztech.springclouduser.helper.PageHelperUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 共享平台数据源配置类
 * Created by zhongjunkai on 18/5/30.
 */
@Configuration
@MapperScan(basePackages = "com.sztech.springclouduser.interMapper",
        sqlSessionTemplateRef = "interfaceSqlSessionTemplate")
public class InterfaceDataSourceConfig {


    @Bean(name = "interfaceDatasource")
    @ConfigurationProperties(prefix = "interface.datasource")
    public DataSource interfaceDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean
    public SqlSessionFactory interfaceSqlSessionFactory(@Qualifier("interfaceDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean interfaceSqlSession = new SqlSessionFactoryBean();
        interfaceSqlSession.setDataSource(dataSource);


        interfaceSqlSession.setPlugins(new Interceptor[]{PageHelperUtils.createPageHelperPlugin()});

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        interfaceSqlSession.setMapperLocations(resolver.getResources("classpath*:com.sztech.springclouduser.interMapper/*"));
        return interfaceSqlSession.getObject();
    }

    @Bean
    public SqlSessionTemplate interfaceSqlSessionTemplate(@Qualifier("interfaceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
    }
}
