package com.example.security.security;

import com.example.security.role.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
