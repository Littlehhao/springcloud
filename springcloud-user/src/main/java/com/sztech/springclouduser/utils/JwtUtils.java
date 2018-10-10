package com.sztech.springclouduser.utils;

import com.sztech.springcloudbean.expection.SpringCloudExpection;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import static org.jose4j.jwk.RsaJwkGenerator.generateJwk;

/**
 * @program: springcloud
 * @description:jwt工具类，用于通过接口获取数据时进行身份验证
 * @author: jiefu
 * @create: 2018-09-13 14:10
 **/
public class JwtUtils {


    //秘钥发行人
    public static final String ISSUER = "cloud";

    //秘钥接收人
    public static final String AUDIENCE = "audience_cloud";

    //秘钥过期时间，单位:分钟
    public static final Integer EXPIRATION_TIME = 12;

    public static final String ERROR_CREATE = "80";

    public static final String ERROR_VALID = "88";

    public static RsaJsonWebKey rsaJsonWebKey ;

    static {
        try {
            rsaJsonWebKey = generateJwk(2048);
        } catch (JoseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成jwt加密串
     * @return
     */
    public static String createJwt(String subject){
        String jwt = null;
        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuer(ISSUER);  // who creates the token and signs it
            claims.setAudience(AUDIENCE); // to whom the token is intended to be sent
            claims.setExpirationTimeMinutesInTheFuture(EXPIRATION_TIME); // time when the token will expire (10 minutes from now)
            claims.setGeneratedJwtId(); // a unique identifier for the token
            claims.setIssuedAtToNow();  // when the token was issued/created (now)
            claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
            claims.setSubject(subject); // the subject/principal is whom the token is about

            JsonWebSignature jws = new JsonWebSignature();

            jws.setPayload(claims.toJson());

            jws.setKey(rsaJsonWebKey.getPrivateKey());

            jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

            jwt = jws.getCompactSerialization();

        } catch (JoseException e) {
            throw new SpringCloudExpection(ERROR_CREATE, "生成秘钥失败");
        }
        return jwt;
    }

    /**
     * 验证jwt加密串，同时返回解密信息
     * @param jwt
     * @return
     */
    public static JwtClaims validJwt(String jwt){
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer(ISSUER) // whom the JWT needs to have been issued by
                .setExpectedAudience(AUDIENCE) // to whom the JWT is intended for
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .setJwsAlgorithmConstraints( // only allow the expected signature algorithm(s) in the given context
                        new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.WHITELIST, // which is only RS256 here
                                AlgorithmIdentifiers.RSA_USING_SHA256))
                .build();
        try{
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            return jwtClaims;
        }catch (InvalidJwtException e){
            if (e.hasExpired()){
                throw new SpringCloudExpection(ERROR_VALID, "JWT expired");
            }

            if (e.hasErrorCode(ErrorCodes.AUDIENCE_INVALID)){
                throw new SpringCloudExpection(ERROR_VALID, "JWT had wrong audience");
            }
            if (e.hasErrorCode(ErrorCodes.ISSUER_INVALID)) {
                throw new SpringCloudExpection(ERROR_VALID, "JWT had wrong issuer");
            }
            throw new SpringCloudExpection(ERROR_VALID, e.getMessage());
        }
    }
}
