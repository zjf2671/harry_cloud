package com.zjf.common.core.constants;

/**
 * 全局公共常量
 * @author Harry
 */
public interface CommonConstant {

    /**
     * 删除
     */
    String STATUS_DEL = "1";

    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";


    /**
     * 超级管理员用户名
     */
    String ADMIN_USER_NAME = "ZYadmin.";

    /**
     * 公共日期格式
     */
    String MONTH_FORMAT = "yyyy-MM";
    String DATE_FORMAT = "yyyy-MM-dd";
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String SIMPLE_MONTH_FORMAT = "yyyyMM";
    String SIMPLE_DATE_FORMAT = "yyyyMMdd";
    String SIMPLE_DATETIME_FORMAT = "yyyyMMddHHmmss";

    String DEF_USER_PASSWORD = "123456";

    String LOCK_KEY_PREFIX = "LOCK_KEY:";

    /**
     * 负载均衡策略-版本号 信息头
     */
    String ZY_VERSION = "zy-version";
    /**
     * 注册中心元数据 版本号
     */
    String METADATA_VERSION = "version";
}
