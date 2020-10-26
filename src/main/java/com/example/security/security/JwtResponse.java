package com.example.security.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String schema = "Bearer";
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
