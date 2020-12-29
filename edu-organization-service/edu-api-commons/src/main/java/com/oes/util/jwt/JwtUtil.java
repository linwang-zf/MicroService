package com.oes.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    public static final Long JWT_TTL = 3600000L; //有效期 一个小时

    public static final String JWT_KEY = "edu-organization"; //Jwt令牌信息

    /*
    * 创建令牌
    * */
    public static String createJWT(String id, String subject, Long ttlMillis){
        //指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前系统时间
        long nowMillis = System.currentTimeMillis();
        //令牌签发时间
        Date now = new Date(nowMillis);
        //如果令牌有效期为null,则默认设置有效期1小时
        if(ttlMillis == null){
            ttlMillis = JwtUtil.JWT_TTL;
        }
        //令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        //生成密钥
        SecretKey secretKey = generalKey();

        //封装Jwt令牌信息
        JwtBuilder builder = Jwts.builder()
                .setId(id)  //唯一ID
                .setSubject(subject)  //主题 可以是JSON
                .setIssuer("admin")   //签发者
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey) //签名算法以及密钥
                .setExpiration(expDate);

        return builder.compact();

    }

    /*
    * 生成加密 secreKey
    * 对密钥进行base64编码
    * */
    public static SecretKey generalKey(){
        byte[] encodedKey = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /*
    * 解析
    * */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
