package com.example.springsecuritylearn.common;

import com.example.springsecuritylearn.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一返回体
 */
@AllArgsConstructor
@Data
public class R {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    /**
     * 时间
     */
    private String time;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 成功，不返回数据
     *
     * @return
     */
    public static R ok() {
        return new R(Code.SUCCESS, "成功", DateUtil.getNowDate(), null);
    }

    /**
     * 成功，返回数据
     *
     * @param data
     * @return
     */
    public static R ok(Object data) {
        return new R(Code.SUCCESS, "成功", DateUtil.getNowDate(), data);
    }

    /**
     * 不返回数据，只返回状态码和信息
     *
     * @param code
     * @param message
     * @return
     */
    public static R send(int code, String message) {
        return new R(code, message, DateUtil.getNowDate(), null);
    }

    public static R send(int code, String message, Object data) {
        return new R(code, message, DateUtil.getNowDate(), data);
    }
}
