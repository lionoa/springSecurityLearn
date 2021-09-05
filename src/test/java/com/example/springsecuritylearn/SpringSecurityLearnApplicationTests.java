package com.example.springsecuritylearn;

import com.example.springsecuritylearn.common.R;
import com.example.springsecuritylearn.entity.Role;
import com.example.springsecuritylearn.entity.User;
import com.example.springsecuritylearn.entity.UserRole;
import com.example.springsecuritylearn.mapper.UserMapper;
import com.example.springsecuritylearn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@EnableTransactionManagement
class SpringSecurityLearnApplicationTests {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void contextLoads() {
        String lionoa123 = bCryptPasswordEncoder.encode("123456");
        System.out.println(lionoa123);
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    void UserMapper() {
        List<User> users = userMapper.getUsers();
        System.out.println(users);
        List<Role> roles = userMapper.getRoles();
        System.out.println(roles);
        List<UserRole> userRoles = userMapper.getUserRoles();
        System.out.println(userRoles);

        System.out.println("==============================");
        User user = userMapper.getUsers("lionoa");
        System.out.println(user);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    void insert() throws Exception {
        int user = userMapper.insertUser(new User(null, "小李", bCryptPasswordEncoder.encode("123456")));
        System.out.println(user);

        int i = userMapper.insertUserRole(new UserRole(null, 7, 2));
        System.out.println(i);

        int hello = userMapper.insertRole(new Role(null, "HELLO"));
        System.out.println(hello);

        throw new Exception("手动抛出异常");
    }
    
    @Test
    void getUserId() {
        User user = new User(null, "helloWorld", "123");
        int i = userMapper.insertUser(user);
        int id = user.getId();
        System.out.println(id);
    }

    @Autowired
    private UserService userService;

    @Test
    void userService() {
        R register = userService.register(new User(null, "lionoa", "lionoa123"));
        System.out.println(register.toString());
    }
}
