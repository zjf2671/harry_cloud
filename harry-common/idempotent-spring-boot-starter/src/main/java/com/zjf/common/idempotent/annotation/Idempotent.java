package com.zjf.common.idempotent.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 幂等注解
 * 思路来源https://github.com/yinjihuan/kitty/blob/master/kitty-distributed/kitty-distributed-lock/src/main/java/com/cxytiandi/kitty/lock/idempotent/aspect/Idempotent.java
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

    /**
     * 锁的名称，唯一性（默认为方法全限定名）
     * @return
     */
    String value() default "";

    /**
     * SPEL表达式，获取幂等Key，默认会从线程上下文中获取框架提供的幂等ID
     * @return
     */
    String spelKey() default "";

    /**
     * 存储过期时间
     * @return
     */
    int storageExpireTime() default 10;

    /**
     * 锁的过期时间 秒
     * @return
     */
    int lockExpireTime() default 10;

    /**
     * 触发幂等限制时调用同类中的方法进行后续处理
     * @return
     */
    String idempotentHandler() default "";

    /**
     * 触发幂等限制时调用其他类中的方法进行后续处理
     * @return
     */
    Class<?>[] idempotentHandlerClass() default {};

}
