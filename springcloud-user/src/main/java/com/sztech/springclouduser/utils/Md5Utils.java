package com.sztech.springclouduser.utils;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @program: springcloud
 * @description: md5加密
 * @author: jiefu
 * @create: 2018-09-13 13:49
 **/
public class Md5Utils {

    public static String md5(String content){
        return DigestUtils.md5DigestAsHex(getContentBytes(content, "utf-8"));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    public static void main(String[] args) {
        System.out.println(md5(("zhuhao")));
    }

}
