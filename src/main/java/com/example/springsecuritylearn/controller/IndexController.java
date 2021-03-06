package com.example.springsecuritylearn.controller;

import com.example.springsecuritylearn.common.R;
import com.example.springsecuritylearn.entity.User;
import com.example.springsecuritylearn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@Slf4j
// 开启方法级安全验证

public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/")
    public R index() {
        // 获取当前登录的用户名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return R.ok("欢迎您 : " + name);
    }

    @GetMapping("/user")
    public R user() {
        // 返回当前用户的所有信息
        return R.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/isAdmin")
    public R isAdmin(HttpServletRequest httpServletRequest) {
        // 返回是否是管理员 默认会添加 ROLE_ 前缀
        return R.ok(httpServletRequest.isUserInRole("ADMIN"));
    }

    // 设置权限访问，非管理员不可访问
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public R admin() {
        return R.ok(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/info")
    public R adminInfo() {
        return R.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/test")
    public R test() {
        return R.ok("跨域测试页面");
    }

    @GetMapping("/register/{username}/{password}")
    public R register(@PathVariable String username, @PathVariable String password) {
        return userService.register(new User(null, username, password));
    }

    @GetMapping("/login/{username}/{password}")
    public R login(@PathVariable("username") String username, @PathVariable("password") String password) {
        return userService.login(username, password);
    }
}
