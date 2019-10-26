package com.zjf.common.core.context;

/**
 * 负载均衡策略Holder
 *
 * @author Harry
 */
public class LbIsolationContextHolder {
    private static final ThreadLocal<String> VERSION_CONTEXT = new InheritableThreadLocal<>();

    public static void setVersion(String version) {
        VERSION_CONTEXT.set(version);
    }

    public static String getVersion() {
        return VERSION_CONTEXT.get();
    }

    public static void clear() {
        VERSION_CONTEXT.remove();
    }
}
