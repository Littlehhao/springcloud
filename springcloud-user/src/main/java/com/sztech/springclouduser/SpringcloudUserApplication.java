package com.sztech.springclouduser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.sztech")
@MapperScan(value = {"com.sztech.springclouduser.mapper"})
@EnableTransactionManagement//开启注解方式的数据库事务
@EnableCaching
@EnableConfigurationProperties
public class SpringcloudUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudUserApplication.class, args);
	}
}
