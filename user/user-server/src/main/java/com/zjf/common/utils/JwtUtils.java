package com.zjf.common.utils;

import com.zjf.common.constants.ErrorCodeEnum;
import com.zjf.common.exception.BusinessException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt工具类
 * @author harry.zhang
 * 
 */
@ConfigurationProperties(prefix = "app.jwt")
@Component
@Slf4j
public class JwtUtils {

    private String secret;
    private Long expire;
    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(String userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        if(StringUtils.isBlank(token)){
            throw new BusinessException(ErrorCodeEnum.NO_TOKEN.getValue(), ErrorCodeEnum.NO_TOKEN.getCode());
        }
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException e){
            throw new BusinessException(ErrorCodeEnum.TOKEN_EXPIRED.getValue(), ErrorCodeEnum.TOKEN_EXPIRED.getCode(), e);
        }catch (MalformedJwtException e){
            throw new BusinessException(ErrorCodeEnum.TOKEN_ERROR.getValue(), ErrorCodeEnum.TOKEN_ERROR.getCode(), e);
        }catch (Exception e){
            throw new BusinessException("validate is token error", HttpStatus.UNAUTHORIZED.value(), e);
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
