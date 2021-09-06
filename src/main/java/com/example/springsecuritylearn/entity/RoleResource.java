package com.example.springsecuritylearn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResource {
    private Integer id;
    private Integer roleId;
    private Integer resourceId;
}
