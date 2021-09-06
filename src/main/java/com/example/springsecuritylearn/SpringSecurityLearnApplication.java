package com.example.springsecuritylearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
// 使用该注解之后 @PreAuthorize 才会生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityLearnApplication.class, args);
    }

}
