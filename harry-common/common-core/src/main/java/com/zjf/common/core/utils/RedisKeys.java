package com.zjf.common.core.utils;

/**
 * Redis所有Keys定义
 *
 * @author harry.zhang
 * 
 */
public class RedisKeys {

    private RedisKeys(){}

    private final static String PREFIX = "hiip:";

    /**
     * redis 系统配置key
     */
    public static final String LOCAL_CONFIG = PREFIX + "LOCAL_CONFIG";

    /** @param key
     * @return
     */
    public static String getSessionKey(String key){
        return PREFIX+"sessionid:user:" + key;
    }

    /**
     * code key
     * @param key
     * @return
     */
    public static String getCodeKey(String key){
        return PREFIX+"code:user:" + key;
    }

    /**
     * token key
     * @param key
     * @return
     */
    public static String getTokenKey(String key){
        return PREFIX+"token:user:" + key;
    }

    /**
     * 锁屏key
     * @param key
     * @return
     */
    public static String getLockKey(String key){
        return PREFIX+"lock:user:" + key;
    }
}
