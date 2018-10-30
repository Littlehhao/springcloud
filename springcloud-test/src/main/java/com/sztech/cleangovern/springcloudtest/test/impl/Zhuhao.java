package com.sztech.cleangovern.springcloudtest.test.impl;

import com.sztech.cleangovern.springcloudtest.test.Man;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-10-12 16:24
 **/
public class Zhuhao implements Man {

    @Override
    public void findObject() {
        System.out.println("select * from girl");
    }
}
