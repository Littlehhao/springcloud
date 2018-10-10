package com.sztech.cleangovern.model.entity;


import java.io.Serializable;
import java.util.Date;

public class TableInfoDO implements Serializable{

    private Integer id;//主键id

    private String deptShortName;//部门短号

    private String tableName;//表名

    private String tableComment;//表的中文名

    private String keyColumn;//主键字段名

    private String md5Column;//所有字段md5加密

    private Date createTime;//创建时间

    private Date ruleUpdateTime;//表规则更改日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptShortName() {
        return deptShortName;
    }

    public void setDeptShortName(String deptShortName) {
        this.deptShortName = deptShortName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    public String getMd5Column() {
        return md5Column;
    }

    public void setMd5Column(String md5Column) {
        this.md5Column = md5Column;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRuleUpdateTime() {
        return ruleUpdateTime;
    }

    public void setRuleUpdateTime(Date ruleUpdateTime) {
        this.ruleUpdateTime = ruleUpdateTime;
    }
}