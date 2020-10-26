package com.example.security.role;

public enum UserRole {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_ANONYMOUS;

    public String forSecurity() {
        return this.name()
                   .replace("ROLE_", "");
    }
}
