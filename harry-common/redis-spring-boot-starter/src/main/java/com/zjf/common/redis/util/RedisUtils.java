package com.zjf.common.redis.util;

import com.zjf.common.redis.templete.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Redis工具类
 *
 * @author harry.zhang
 * 
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24L;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;

    public void set(String key, Object value, long expire){
        redisRepository.setExpire(key, value, expire);
    }
    public void updateKeyExpire(String key, long expire){
        redisRepository.updateKeyExpire(key, expire);
    }
    public void set(String key, Object value){
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz) {
        T value = (T)redisRepository.get(key);
        return value;
    }

    /**
     * 只支持基本类型值
     * @param key
     * @return
     */
    public String get(String key) {
        Object value = redisRepository.get(key);
        return value != null? String.valueOf(value) : null;
    }

    public void delete(String key) {
        redisRepository.del(key);
    }

}
