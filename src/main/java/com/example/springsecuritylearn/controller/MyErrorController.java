package com.example.springsecuritylearn.controller;

import com.example.springsecuritylearn.common.Code;
import com.example.springsecuritylearn.common.R;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public R error(HttpServletResponse response) {
        int status = response.getStatus();
        if (status == 403) {
            return R.send(Code.FORBIDDEN, "拒绝访问，请先登录！");
        }
        if (status == 404) {
            return R.send(Code.NOT_FOUND, "该页面不存在，请重试！");
        }
        if (status == 500) {
            return R.send(Code.ERROR, "服务器出错，请重试！");
        }
        return R.send(Code.UN_KNOW_ERROR, "未知异常，请联系管理员");
    }
}
