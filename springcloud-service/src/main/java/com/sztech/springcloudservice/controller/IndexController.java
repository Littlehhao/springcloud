package com.sztech.springcloudservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-09-13 10:07
 **/
@RequestMapping("test")
@RestController
public class IndexController {

    @RequestMapping("test")
    public String test(){
        return "hello world 123";
    }
}
