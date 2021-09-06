package com.example.springsecuritylearn.service.Impl;

import com.example.springsecuritylearn.common.Code;
import com.example.springsecuritylearn.common.R;
import com.example.springsecuritylearn.entity.User;
import com.example.springsecuritylearn.entity.UserRole;
import com.example.springsecuritylearn.mapper.UserMapper;
import com.example.springsecuritylearn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public R login(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return R.ok(authenticate.getPrincipal());
    }

    // 默认注册的都是 USER 权限
    @Override
    @Transactional
    public R register(User user) {
        log.info("正在注册用户：{} {}", user.getUsername(), user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        int result = userMapper.insertUser(user);
        int id = user.getId();
        int i = userMapper.insertUserRole(new UserRole(null, id, 1));
        if (result == 1 && i == 1) {
            return R.ok();
        }
        log.info("注册失败");
        return R.send(Code.ERROR, "注册失败！");
    }
}
