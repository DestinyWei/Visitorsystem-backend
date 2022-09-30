package com.project.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-09-30-11:42
 * @version:
 */
@Component
public class JwtUtils {

    //定义token返回头部
    public static String header = "token";

    //token前缀
    public static String tokenPrefix = "Bearer ";

    //签名密钥
    public static String secret = "abcdefg123456789";

    //有效期
    public static long expireTime = 1000*60*60*24;

    //存进客户端的token的key名
    public static final String USER_LOGIN_TOKEN = "USER_LOGIN_TOKEN";

    /**
     * 创建TOKEN
     * @param subject
     * @return
     */
    public static String createToken(String subject){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expireTime);//过期时间

        return tokenPrefix + Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证token是否过期失效
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }
    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }

    // --------------------- getter & setter ---------------------

    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public long getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
}