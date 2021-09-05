package com.example.springsecuritylearn.common;

/**
 * 状态码
 */
public class Code {

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 请求成功，但是用户名或密码错误
     */
    public static final int BAD_CREDENTIALS = 201;

    /**
     * 用户名唯一
     */
    public static final int USERNAME_UNIQUE = 202;

    /**
     * 拒绝访问，请先登录
     */
    public static final int FORBIDDEN = 403;

    /**
     * 权限不足
     */
    public static final int ACCESS_DENIED = 4031;

    /**
     * 资源不存在
     */
    public static final int NOT_FOUND = 404;

    /**
     * 服务器报错
     */
    public static final int ERROR = 500;

    /**
     * 未知错误
     */
    public static final int UN_KNOW_ERROR = 1000;

}
