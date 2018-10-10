package com.sztech.springclouduser.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sztech.springcloudbean.commen.Constants;
import com.sztech.springcloudbean.expection.SpringCloudExpection;
import com.sztech.springcloudbean.response.AccessTokenResult;
import com.sztech.springcloudbean.response.Result;
import com.sztech.springcloudbean.response.ResultUtils;
import com.sztech.springclouduser.bean.Authc;
import com.sztech.springclouduser.service.IAuthcService;
import com.sztech.springclouduser.utils.DateTimeUtils;
import com.sztech.springclouduser.utils.JwtToken;
import com.sztech.springclouduser.utils.Md5Utils;
import com.sztech.springclouduser.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-09-12 16:25
 **/
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private IAuthcService authcService;


        @RequestMapping("authentication")
        public AccessTokenResult authentication(Authc authc){
            authc.setSecurekey(Md5Utils.md5((authc.getSubjectKey())));
            //根据主体身份查询信息
            Authc authc1=authcService.findAuth(authc);
            if(null==authc1){
                throw new SpringCloudExpection(Constants.ResultEnums.ERROR.getCode(),"身份验证异常");
            }
            try {
                //访问秘钥到期时间
                Date accesTokenExpir=DateTimeUtils.getTimeAddSeconds(JwtToken.ACCESS_TOKEN_EXPIRE_SECONDS);
                //设置访问token，访问秘钥
                String accessToken=JwtToken.createToken(authc.getSubjectId(),null,JwtToken.ACCESS_TOKEN_EXPIRE_SECONDS);
                //将token保存咋redis中并设置过期时间
                redisUtils.set(JwtToken.generateAccessTokenKeyOfRedis(authc.getSubjectId()),accessToken,JwtToken.ACCESS_TOKEN_EXPIRE_SECONDS);
                //获取访问秘钥成功
                AccessTokenResult result=AccessTokenResult.getSuccess(authc.getSubjectId(),accessToken);
                //生成刷新秘钥
                String refreshToken=JwtToken.createToken(authc.getSubjectId(),null,JwtToken.REFRESH_TOKEN_EXPIRE_SECONDS);
                //将刷新秘钥保存在redis中
                redisUtils.set(JwtToken.generateRefreshTokenOfRedis(authc.getSubjectId()),refreshToken,JwtToken.REFRESH_TOKEN_EXPIRE_SECONDS);
                //设置过期时间
                result.setExpireTime(accesTokenExpir);
                //设置刷新秘钥
                result.setRefreshToken(refreshToken);
                return result;
            }catch (Exception e){
                throw new SpringCloudExpection(Constants.ResultEnums.ERROR.getCode(),"获取token发生异常");
            }
        }


    /**
     * 根据刷新秘钥来设置请求秘钥
     * @return
     */
    @RequestMapping("refreshToken")
    public AccessTokenResult refreshToken(@RequestParam("subjectId") String subjectId,
                                           @RequestParam("refreshToken") String refreshToken){
        try {
            //验证刷新秘钥是否有效
            JwtToken.verify(refreshToken,subjectId);
            //从redis中取出刷新秘钥
            String token=redisUtils.get(JwtToken.generateRefreshTokenOfRedis(subjectId));
            //从redis中取出刷新秘钥剩余时间
            Long expireTime=redisUtils.getExpireTime(JwtToken.generateRefreshTokenOfRedis(subjectId));
            //将redis取出的刷新秘钥与刷新秘钥比较，不相等则抛出异常刷新秘钥过期，相等就返回新的刷新秘钥
            if (StringUtils.isNotEmpty(token) && StringUtils.equals(token,refreshToken)){
                //访问秘钥到期时间
                Date accesTokenExpir=DateTimeUtils.getTimeAddSeconds(JwtToken.ACCESS_TOKEN_EXPIRE_SECONDS);
                //设置访问token，访问秘钥
                String accessToken=JwtToken.createToken(subjectId,null,JwtToken.ACCESS_TOKEN_EXPIRE_SECONDS);
                //将token保存咋redis中并设置过期时间
                redisUtils.set(JwtToken.generateAccessTokenKeyOfRedis(subjectId),accessToken,JwtToken.ACCESS_TOKEN_EXPIRE_SECONDS);
                //获取访问秘钥成功
                AccessTokenResult result=AccessTokenResult.getSuccess(subjectId,accessToken);
                //设置过期时间
                result.setExpireTime(accesTokenExpir);
                //如果redis刷新秘钥时间小于原来时间的一半，就从新生成刷新秘钥
                if(expireTime<JwtToken.REFRESH_TOKEN_EXPIRE_SECONDS/2){
                    //删除redis中的刷新秘钥
                    redisUtils.delete(JwtToken.generateRefreshTokenOfRedis(subjectId));
                    //生成新得刷新秘钥
                    String refreshTokento=JwtToken.createToken(subjectId,null,JwtToken.REFRESH_TOKEN_EXPIRE_SECONDS);
                    //将刷新秘钥保存在redis中
                    redisUtils.set(JwtToken.generateRefreshTokenOfRedis(subjectId),refreshTokento,JwtToken.REFRESH_TOKEN_EXPIRE_SECONDS);
                    //将刷新秘钥赋入结果集
                    result.setRefreshToken(refreshTokento);
                }else{
                    //如果redis刷新秘钥时间大于原来时间的一半，就还用之前的刷新秘钥
                    result.setRefreshToken(refreshToken);
                }
                return result;
            }else{
                throw new SpringCloudExpection(Constants.ResultEnums.ERROR.getCode(),"刷新秘钥过期，请重新登录");
            }
        }catch (JWTVerificationException e){
            throw new SpringCloudExpection(Constants.ResultEnums.ERROR.getCode(),"refreshToken无效或已过期");
        }catch (UnsupportedEncodingException e){
            throw new SpringCloudExpection(Constants.ResultEnums.ERROR.getCode(),"refreshToken认证异常");
        }
    }


    /**
     * 注销用户
     * @return
     */
    @PostMapping("logOut")
    public Result logOut(@RequestParam("subjectId") String subjectId){
        //从redis中删除认证token
        redisUtils.delete(JwtToken.generateAccessTokenKeyOfRedis(subjectId));
        return ResultUtils.success();
    }
}
