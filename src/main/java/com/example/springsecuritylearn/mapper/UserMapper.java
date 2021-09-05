package com.example.springsecuritylearn.mapper;

import com.example.springsecuritylearn.entity.Role;
import com.example.springsecuritylearn.entity.User;
import com.example.springsecuritylearn.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUsers();
    User getUsers(String username);
    List<Role> getRoles();
    List<UserRole> getUserRoles();
    String getAuthority(Integer userId);
    User getUserByUsername(String username);
    int insertUser(User user);
    int insertUserRole(UserRole userRole);
    int insertRole(Role role);
}
