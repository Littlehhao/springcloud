package com.sztech.springcloudservice2.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-09-12 14:25
 **/
@RestController
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("index")
    @HystrixCommand(fallbackMethod = "helloError")
    public String index(){
        String list=restTemplate.getForObject("http://provider/test/test/",String.class);
        return list;
        //return ResultUtils.success("1","成功");
    }

    public String helloError(){
        return "hello error";
    }
}
