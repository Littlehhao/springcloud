package com.sztech.springclouduser.service.imp;

import com.sztech.springclouduser.bean.Authc;
import com.sztech.springclouduser.interMapper.AuthcMapper;
import com.sztech.springclouduser.service.IAuthcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-09-13 11:12
 **/
@Service
public class AuthcServcieImp implements IAuthcService {

    @Autowired
    private AuthcMapper authcMapper;

    @Override
    public Authc findAuth(Authc authc) {
        return authcMapper.findAuth(authc);
    }
}
