package com.sztech.springclouduser.bean;

/**
 * @program: springcloud
 * @description: 规则信息实体bean
 * @author: jiefu
 * @create: 2018-09-12 11:36
 **/
public class RuleInfo {

    private Integer id;

    private String ruleName;

    public RuleInfo() {
    }

    public RuleInfo(Integer id, String ruleName) {
        this.id = id;
        this.ruleName = ruleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
