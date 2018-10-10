package com.sztech.springcloudbean.expection;

import lombok.Data;

/**
 * @program: springcloud
 * @description: 异常工具类
 * @author: jiefu
 * @create: 2018-09-12 10:42
 **/
@Data
public class SpringCloudExpection extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SpringCloudExpection(String code,String msg) {
        super(msg);
        this.code = code;
    }
}
