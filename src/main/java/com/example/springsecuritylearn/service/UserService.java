package com.example.springsecuritylearn.service;

import com.example.springsecuritylearn.common.R;
import com.example.springsecuritylearn.entity.User;

public interface UserService {
    User getUserByUsername(String username);
    R login(String username, String password);
    int register(User user);
}
