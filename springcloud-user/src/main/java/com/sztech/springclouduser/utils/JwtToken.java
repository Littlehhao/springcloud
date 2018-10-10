package com.sztech.springclouduser.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @program: springcloud
 * @description:通过jwt包实现jwt加密与认证
 * @author: jiefu
 * @create: 2018-09-13 14:03
 **/
public class JwtToken {

    //秘钥，通过该秘钥对token进行加密，注意保密这个秘钥
    private static final String SECRET = "cloud_jwt_secret.";
    //redis中保存accessToken的key
    public static final String ACCESS_TOKEN = "accessToken";
    //redis中保存refreshToken的key
    public static final String REFRESH_TOKEN = "refreshToken";

    //访问秘钥过期时间，单位：秒
    public static final int ACCESS_TOKEN_EXPIRE_SECONDS = 60 * 60 * 12;

    //刷新秘钥过期时间，单位：秒
    public static final int REFRESH_TOKEN_EXPIRE_SECONDS = ACCESS_TOKEN_EXPIRE_SECONDS * 4;


    /**
     * 秘钥使用用户 代表那个用户
     * @param subject
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createToken(String subject) throws UnsupportedEncodingException {
        return createToken(subject, null);
    }

    /**
     *
     * @param subject
     * @param code
     * @param expireSeconds
     * @return
     */
    public static String createToken(String subject, String code, Integer expireSeconds) throws UnsupportedEncodingException {
        Date iatDate = new Date();

        Calendar now = Calendar.getInstance(Locale.CHINA);
        if(null != expireSeconds) {
            now.add(Calendar.SECOND, expireSeconds);
        }else {
            now.add(Calendar.SECOND, ACCESS_TOKEN_EXPIRE_SECONDS);
        }
        Date expiresDate = now.getTime();

        //jwt token header
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        //生成jwt token字符串
        JWTCreator.Builder builder = JWT.create().withHeader(map)
                .withClaim("sub", subject)//jwt token所面向的用户
                .withClaim("audience", JwtUtils.AUDIENCE)//接收jwt的一方
                .withClaim("issuer", JwtUtils.ISSUER);//jwt token签发人
        if(StringUtils.isNotEmpty(code)){
            builder.withClaim("code", code);
        }
        String token = builder.withExpiresAt(expiresDate)//设置过期时间
                .withIssuedAt(iatDate)//设置签发时间
                .sign(Algorithm.HMAC256(SECRET));//加密
        return token;
    }


    /**
     * 生成token
     * @param subjectId 秘钥使用用户
     * @param code 秘钥使用用户申请码（用于获取秘钥）
     * @return
     */
    public static String createToken(String subjectId, String code) throws UnsupportedEncodingException {
        return createToken(subjectId, code, null);
    }

    /**
     * 验证token是否有效
     * @param token 加密串
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String, Claim> verifyToken(String token) throws UnsupportedEncodingException, JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt;
        jwt = verifier.verify(token);
        return jwt.getClaims();

    }

    /**
     * 验证token是否有效
     * @param token token加密串
     * @param subjectId 要对比的subjectId参数
     * @return
     * @throws JWTVerificationException
     */
    public static boolean verify(String token, String subjectId) throws JWTVerificationException, UnsupportedEncodingException{
        boolean valid = false;
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt;
        jwt = verifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        if(subjectId.equals(map.get("sub").asString())) {
            valid = true;
        }
        return valid;
    }

    /**
     * 生成保存到redis中的访问秘钥的key
     * @param subjectId 身份主体ID
     * @return
     */
    public static String generateAccessTokenKeyOfRedis(String subjectId) {
        return ACCESS_TOKEN + "-" + subjectId;
    }

    /**
     * 生成保存到redis中的刷新秘钥的key
     * @param subjectId 身份主体ID
     * @return
     */
    public static String generateRefreshTokenOfRedis(String subjectId) {
        return REFRESH_TOKEN + "-" + subjectId;
    }


}
