package com.sztech.springclouduser.controller;

import com.sztech.springcloudbean.response.Result;
import com.sztech.springcloudbean.response.ResultUtils;
import com.sztech.springclouduser.bean.RuleInfo;
import com.sztech.springclouduser.service.IRuleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-09-12 11:40
 **/
@RestController
@RequestMapping("rule")
public class RuleInfoController {

    @Autowired
    private IRuleInfoService ruleInfoService;

    @RequestMapping("list")
    public Result<List<RuleInfo>> select(){
        List<RuleInfo> list=ruleInfoService.select();
        return ResultUtils.success(list);
    }
}
