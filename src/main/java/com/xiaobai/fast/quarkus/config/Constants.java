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
     * 自定义权限校验名称
     */
    public static String CUSTOM_PERMISSION_NAME = "CUSTOM_PERMISSION_NAME";
    /**
     * 自定义权限校验actions
     */
    public static String CUSTOM_PERMISSION_ACTIONS = "CUSTOM_PERMISSION_ACTIONS";
    public static String ClaimName = "userInfo";
    public static String Authentication = "Authentication";


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
