package com.sztech.springclouduser.service.imp;

import com.sztech.springclouduser.bean.RuleInfo;
import com.sztech.springclouduser.mapper.RuleInfoMapper;
import com.sztech.springclouduser.service.IRuleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springcloud
 * @description: 规则信息业务类
 * @author: jiefu
 * @create: 2018-09-12 11:37
 **/
@Service
public class RuleInfoServiceImp implements IRuleInfoService {

    @Autowired
    private RuleInfoMapper ruleInfoMapper;

    @Override
    public List<RuleInfo> select() {
        return ruleInfoMapper.select();
    }
}
