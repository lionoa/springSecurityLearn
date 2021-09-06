package com.example.springsecuritylearn.controller;

import com.example.springsecuritylearn.common.R;
import com.example.springsecuritylearn.mapper.RoleResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @GetMapping("/getUserResources")
    public R getUserResources() {
        List<String> resources = roleResourceMapper.getUserResources();
        return R.ok(resources);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAdminResources")
    public R getAdminResources() {
        List<String> resources = roleResourceMapper.getAdminResources();
        return R.ok(resources);
    }
}
