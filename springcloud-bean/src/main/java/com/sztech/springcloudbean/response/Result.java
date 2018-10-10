package com.sztech.springcloudbean.response;

/**
 * @program: springcloud
 * @description: 返回结果状态
 * @author: jiefu
 * @create: 2018-09-12 10:49
 **/
public class Result<T> {

    private String code;

    private String msg;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
