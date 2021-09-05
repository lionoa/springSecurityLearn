package com.example.springsecuritylearn.service;

import com.example.springsecuritylearn.entity.MyUserDetails;
import com.example.springsecuritylearn.entity.User;
import com.example.springsecuritylearn.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(s);
        if (user == null) {
            // 该异常抛出后会进行处理，变成 BadCredentialsException 用户名或密码错误
            throw new UsernameNotFoundException("用户名不存在");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userMapper.getAuthority(user.getId())));
        return new MyUserDetails(user.getUsername(), user.getPassword(), authorities);
    }
}
