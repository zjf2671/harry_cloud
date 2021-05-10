package com.zjf.common.hystrix.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程共享 data Context
 * @author Harry
 */
public class DCThreadLocalUtil {

    private static final ThreadLocal<Map<String,Object>> DCMap = new ThreadLocal<Map<String, Object>>(){
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    public static Map<String,Object> get(){
        return DCMap.get();
    }

    public static Object get(String key) {
        return DCMap.get().get(key);
    }

    public static void set(String key, String value){
        DCMap.get().put(key,value);
    }

    public static void setMap(Map<String, Object> map){
        DCMap.set(map);
    }

    public static void clear(){
        DCMap.remove();
    }
}
