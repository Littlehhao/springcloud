package com.sztech.springcloudbean.response;

import com.sztech.springcloudbean.commen.Constants;

import java.util.Date;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-09-13 14:57
 **/
public class AccessTokenResult<T> extends Result {

    /**
     * 用户身份主体ID
     */
    private String subjectId;

    /**
     * 访问秘钥
     */
    private String accessToken;

    /**
     * 刷新秘钥
     */
    private String refreshToken;

    /**
     * 失效时间
     */
    private Date expireTime;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
    public static AccessTokenResult getSuccess(String subjectId, String accessToken){
        AccessTokenResult result = new AccessTokenResult();
        result.setCode(Constants.ResultEnums.SUCCESS.getCode());
        result.setMsg("获取访问秘钥成功");
        result.setAccessToken(accessToken);
        result.setSubjectId(subjectId);
        return result;
    }


    public static AccessTokenResult getFailure(String subjectId) {
        AccessTokenResult result = new AccessTokenResult();
        result.setCode(Constants.ResultEnums.ERROR.getCode());
        result.setMsg("获取授权码失败，请检查身份信息!");
        result.setSubjectId(subjectId);
        return result;
    }
}
