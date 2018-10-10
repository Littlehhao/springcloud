package com.sztech.springclouduser.bean;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-09-13 11:04
 **/
public class Authc {

    private Integer id;

    private String subjectId;

    //主体身份密码
    private String subjectKey;

    //主体身份MD5加密密码
    private String securekey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSecurekey() {
        return securekey;
    }

    public void setSecurekey(String securekey) {
        this.securekey = securekey;
    }

    public String getSubjectKey() {
        return subjectKey;
    }

    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }


}
