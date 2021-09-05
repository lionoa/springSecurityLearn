package com.example.springsecuritylearn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private Integer id;
    private Integer user_id;
    private Integer role_id;
}
