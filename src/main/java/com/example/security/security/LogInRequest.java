package com.example.security.security;

import lombok.Data;

@Data
public class LogInRequest {
    private String email;
    private String password;
    private String name;
}
