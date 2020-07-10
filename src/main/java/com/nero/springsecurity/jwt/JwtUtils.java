package com.nero.springsecurity.jwt;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtils {
    //过期时间
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    //私钥
    private static final String TOKEN_SECRET = "privateKey";

    /**
     * JWT的验证结果：过期或无效或有效
     */
    public enum JWT_STATE {
        EXPIRE,
        INVALID,
        VALID,
        ERROR
    }

    /**
     * 签发JWT
     *
     * @param id
     * @param subject   可以是JSON数据 尽可能少
     * @param ttlMillis
     * @return String
     */
    public static String createJWT(String id, String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expireDate = new Date(nowMillis + JwtUtils.EXPIRE_TIME);
        Map<String, Object> claims = new HashMap<>();
        claims.put("uuid", UUID.randomUUID().toString());//自定义消息
        return Jwts.builder()
                .setId(id)
                .setSubject(subject) // 主题
                .setIssuer("user") // 签发者
                .setIssuedAt(now) // 签发时间
                .setExpiration(expireDate)//有效时间
                .setClaims(claims)
                .signWith(signatureAlgorithm, JwtUtils.TOKEN_SECRET) // 签名算法以及密匙
                .compact();
    }

    /**
     * 验证JWT
     *
     * @param jwtStr
     * @return
     */
    public static JWT_STATE validateJWT(String jwtStr) {
        try {
            parseJWT(jwtStr);
            return JWT_STATE.VALID;//token 有效
        } catch (ExpiredJwtException e) {
            return JWT_STATE.EXPIRE;//token 过期
        } catch (SignatureException e) {
            return JWT_STATE.INVALID;//token 无效
        } catch (Exception e) {
            return JWT_STATE.ERROR;//解析token发送系统错误
        }
    }

    /**
     * 校验签名和获取Claimsx信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(JwtUtils.TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAt(String token) {
        return parseJWT(token).getIssuedAt();
    }

    /**
     * 获取自定义信息
     */
    public static Object getCostomInfo(String token, String info) {
        return parseJWT(token).get(info);
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpiration(String token) {
        return parseJWT(token).getExpiration();
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public static boolean isExpired(String token) {
        try {
            final Date expiration = getExpiration(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

}
