package com.example.springsecuritylearn.exception;

import lombok.extern.slf4j.Slf4j;
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
    public String myExceptionHandler(Exception e) {
        e.printStackTrace();
        if (e instanceof BadCredentialsException) {
            return "用户名或密码错误！";
        }
        return "页面访问异常，请重试！";
    }
}
