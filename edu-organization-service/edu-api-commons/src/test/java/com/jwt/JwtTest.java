package com.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
* 令牌的生成和解析
* */
public class JwtTest {
    /*
    * 创建令牌
    * */
    @Test
    public void testCreateJwt(){
        //创建令牌对象
        JwtBuilder builder = Jwts.builder();
        builder.setIssuer("edu-origanization"); //颁发者
        builder.setIssuedAt(new Date()); //颁发时间
        builder.setExpiration(new Date(System.currentTimeMillis()+40000));//过期时间，当前时间加20秒
        builder.setSubject("JWT令牌测试"); //主题信息

        //自定义载荷信息
        Map<String,Object> userInfo = new HashMap<String,Object>();
        userInfo.put("user","admin");
        builder.addClaims(userInfo);//添加载荷

        builder.signWith(SignatureAlgorithm.HS256, "edu-organization");//1 签名算法，2 密钥
        System.out.println(builder.compact());//输出密文
    }

    /**
     * 令牌解析
     */
    @Test
    public void parseToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJlZHUtb3JpZ2FuaXphdGlvbiIsImlhdCI6MTYwODI5NDAwOSwiZXhwIjoxNjA4Mjk0MDQ5LCJzdWIiOiJKV1Tku6TniYzmtYvor5UiLCJ1c2VyIjoiYWRtaW4ifQ.ScXMNFs_dyOe4jpmIwDoykWmCaKjiJkmlI7FXL0A4Ac";
        Claims claims = Jwts.parser().setSigningKey("edu-organization") //密钥
                .parseClaimsJws(token) //要解析的令牌对象
                .getBody();//获取解析后数据
        System.out.print(claims);
    }
}
