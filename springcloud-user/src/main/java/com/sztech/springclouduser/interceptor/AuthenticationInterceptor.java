package com.sztech.springclouduser.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sztech.springcloudbean.commen.Constants;
import com.sztech.springcloudbean.expection.SpringCloudExpection;
import com.sztech.springclouduser.service.IAuthcService;
import com.sztech.springclouduser.service.imp.AuthcServcieImp;
import com.sztech.springclouduser.utils.JwtToken;
import com.sztech.springclouduser.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-09-12 16:52
 **/
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //默认ajax会发送两次请求，第一次为options请求，让其通过
        if(StringUtils.equals(request.getMethod(), RequestMethod.OPTIONS.name())){
            return true;
        }
        String subjectId=request.getHeader("subjectId");
        String accessToken=request.getHeader("accessToken");
        if (StringUtils.isEmpty(subjectId) || StringUtils.isEmpty(accessToken)){
            throw new SpringCloudExpection(Constants.ResultEnums.ERROR.getCode(),"subjectId或accessToken不能为空");
        }
        try {
            //从redis中取出token
            String token=redisUtils.get(JwtToken.generateAccessTokenKeyOfRedis(subjectId));
            //验证token是否有效
            JwtToken.verify(accessToken,subjectId);
            if(!StringUtils.equals(accessToken,token) || StringUtils.isEmpty(token)){
                throw new SpringCloudExpection(Constants.ResultEnums.ERROR.getCode(),"非法的accesstoken");
            }
        }catch (JWTVerificationException e){
            throw new SpringCloudExpection(Constants.ResultEnums.ERROR.getCode(),"token无效或已过期");
        }catch (UnsupportedEncodingException e){
            throw new SpringCloudExpection(Constants.ResultEnums.ERROR.getCode(),"token认证异常");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
