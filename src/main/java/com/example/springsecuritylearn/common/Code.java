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
    public static final int BadCredentials = 201;

    /**
     * 拒绝访问，没有权限
     */
    public static final int FORBIDDEN = 403;

    /**
     * 资源不存在
     */
    public static final int NOT_FOUND = 404;

    /**
     * 服务器报错
     */
    public static final int ERROR = 500;

}
