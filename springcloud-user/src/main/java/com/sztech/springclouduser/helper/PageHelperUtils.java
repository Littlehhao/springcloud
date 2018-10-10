package com.sztech.springclouduser.helper;

import com.github.pagehelper.PageHelper;

import java.util.Properties;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-09-12 11:00
 **/
public class PageHelperUtils {


    public static PageHelper createPageHelperPlugin(){
        //分页插件,插件无非是设置mybatis的拦截器
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
