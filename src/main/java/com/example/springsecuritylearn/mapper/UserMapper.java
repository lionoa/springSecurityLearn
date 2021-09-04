package com.example.springsecuritylearn.mapper;

import com.example.springsecuritylearn.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);

    @Insert("insert into user (id,username,password,authority) values (null,#{username},#{password},#{authority})")
    int insert(User user);
}
