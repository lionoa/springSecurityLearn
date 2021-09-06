package com.example.springsecuritylearn.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleResourceMapper {
    List<String> getUserResources();
    List<String> getAdminResources();
}
