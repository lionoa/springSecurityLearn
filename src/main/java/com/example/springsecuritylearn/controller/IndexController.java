package com.example.springsecuritylearn.controller;

import com.example.springsecuritylearn.common.R;
import com.example.springsecuritylearn.entity.User;
import com.example.springsecuritylearn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
// 开启方法级安全验证
// 使用该注解之后 @PreAuthorize 才会生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/")
    public String index() {
        // 获取当前登录的用户名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return "欢迎您 : " + name;
    }

    @GetMapping("/user")
    public Authentication user() {
        // 返回当前用户的所有信息
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/isAdmin")
    public boolean isAdmin(HttpServletRequest httpServletRequest) {
        // 打印当前登录的用户名
        log.info("当前登录用户：{}", httpServletRequest.getRemoteUser());
        // 返回是否是管理员 默认会添加 ROLE_ 前缀
        return httpServletRequest.isUserInRole("ADMIN");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        // 设置权限访问，非管理员不可访问
        return "欢迎您，管理员：" + SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/info")
    public Object adminInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // 如果不是 ROLE_ 开始的话就加上，是 ROLE_ 开始的话就直接使用
    @GetMapping("/AllUser")
    public Object allUser() {
        // 获取当前登录的所有用户
        return sessionRegistry.getAllPrincipals();
    }

    @GetMapping("/test")
    public String test() {
        return "跨域测试页面";
    }

    @GetMapping("/register/{username}/{password}")
    public Integer register(@PathVariable String username, @PathVariable String password) {
        return userService.register(new User(null, username, password, "USER"));
    }

    @GetMapping("/login/{username}/{password}")
    public R login(@PathVariable("username") String username, @PathVariable("password") String password) {
        return userService.login(username, password);
    }
}