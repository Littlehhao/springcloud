package com.sztech.cleangovern.springcloudtest.test.impl;

import com.sztech.cleangovern.springcloudtest.test.Man;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-10-12 16:25
 **/
public class ManHandler implements InvocationHandler {

    private Man man;

    public ManHandler(Man man) {
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(man,null);
        after();
        return null;
    }

    public void after(){
        System.out.println("没找你之前");
    }

    public void before(){

        System.out.println("找到你之后");

    }
}
