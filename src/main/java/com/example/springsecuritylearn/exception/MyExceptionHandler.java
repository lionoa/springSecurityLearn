package com.example.springsecuritylearn.exception;

import com.example.springsecuritylearn.common.Code;
import com.example.springsecuritylearn.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    // 对 RequestMapping 的异常进行处理
    // 针对响应码 200 的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回的是信息，不是页面
    public R myExceptionHandler(Exception e) {
        log.info(e.getClass().getName());
        if (e instanceof BadCredentialsException) {
            return R.send(Code.BAD_CREDENTIALS, "用户名或密码错误！");
        }
        if (e instanceof DuplicateKeyException) {
            return R.send(Code.USERNAME_UNIQUE, "用户名已存在！");
        }
        if (e instanceof AccessDeniedException) {
            return R.send(Code.ACCESS_DENIED, "权限不足");
        }
        return R.send(Code.UN_KNOW_ERROR, "页面访问异常，请重试！");
    }
}
