package com.xiaobai.fast.quarkus.config;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class Constants {
    /**
     * admin role key
     */
    public static final String ADMIN_ROLE_KEY = "admin";
    /**
     * admin 权限标识符号
     */
    public static final String ADMIN_PERMISSION_KEY = "****";
    /**
     * jwt issuer
     */
    public static final String JWT_ISSUER = "fast-quarkus";
    /**
     * jwt upn
     */
    public static final String JWT_UPN = "fast-quarkus";
    /**
     * 字符串常量
     */
    public static final String DOT = ".";
    public static final String EMPTY = "";



    /**
     * 代码来自hutool.cn
     * 字符常量：斜杠 {@code '/'}
     */
   public static final char SLASH = '/';
    /**
     * 代码来自hutool.cn
     * 字符常量：反斜杠 {@code '\\'}
     */
    public static final char BACKSLASH = '\\';
    /**
     * 自定义权限校验名称
     */
    public static String CUSTOM_PERMISSION_NAME = "CUSTOM_PERMISSION_NAME";
    /**
     * 自定义权限校验actions
     */
    public static String CUSTOM_PERMISSION_ACTIONS = "CUSTOM_PERMISSION_ACTIONS";
    public static String ClaimName = "userInfo";
    public static String Authentication = "Authentication";

    /**
     * 本地缓存cacheKey
     */
    public static class CacheKey{

        /**
         * 字典数据缓存
         */
        public static final String DICT_DATA_CACHE_NAME = "DictDataCacheName";
    }


    public static String likeFormat(String fieldName) {
        return "%"+fieldName+"%";
    }
    public static String likeLeftFormat(String fieldName) {
        return "%"+fieldName;
    }
    public static String likeRightFormat(String fieldName) {
        return "%"+fieldName+"%";
    }
}
