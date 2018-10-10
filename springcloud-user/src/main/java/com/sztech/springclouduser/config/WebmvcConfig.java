package com.sztech.springclouduser.config;

import com.sztech.springclouduser.interceptor.AuthenticationInterceptor;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: springcloud
 * @description: mvc过滤器
 * @author: jiefu
 * @create: 2018-09-12 16:43
 **/
@Configuration
public class WebmvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public AuthenticationInterceptor authInterceptor(){
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/rule/**")
                .excludePathPatterns("/login/authentication");
        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE", "GET", "POST")
                .allowedHeaders("*")
                .exposedHeaders("access-control-allow-headers",
                        "access-control-allow-methods",
                        "access-control-allow-origin");
        super.addCorsMappings(registry);
    }
}
