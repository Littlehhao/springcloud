package com.sztech.cleangovern.model.helper;


import java.io.Serializable;

/**
 * @program: cleangovern
 * @description: 校核管理查询入参封装类
 * @author: jiefu
 * @create: 2018-09-25 11:02
 **/
public class CheckMangerSearcher extends PageSearcher implements Serializable {

    private String feedbackResult;//问题核对结果类型

    private String startTime;//开始时间

    private String endTime;//结束时间

    private String search;//模糊查询搜索条件

    private String deptCode;

    private String userToken;

    public String getFeedbackResult() {
        return feedbackResult;
    }

    public void setFeedbackResult(String feedbackResult) {
        this.feedbackResult = feedbackResult;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
