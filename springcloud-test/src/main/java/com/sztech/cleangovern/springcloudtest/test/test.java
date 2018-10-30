package com.sztech.cleangovern.springcloudtest.test;

import com.sztech.cleangovern.springcloudtest.test.impl.ManHandler;
import com.sztech.cleangovern.springcloudtest.test.impl.Zhuhao;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-10-12 16:30
 **/
public class test {

    public static void main(String[] args) {
        Man man=new Zhuhao();
        ManHandler manHandler=new ManHandler(man);
        Man proxyMan=(Man) Proxy.newProxyInstance(man.getClass().getClassLoader(),new Class[]{Man.class},manHandler);
        System.out.println(proxyMan.getClass().getName());
        proxyMan.findObject();
        createProxyClassFile(Man.class);
    }


    private static void createProxyClassFile(Class c){
        byte[] date=ProxyGenerator.generateProxyClass("$proxy0",new Class[]{c});
        //写入文件中
        try {
            FileOutputStream fileOutputStream=new FileOutputStream("$proxy0.class");
            fileOutputStream.write(date);
            fileOutputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
