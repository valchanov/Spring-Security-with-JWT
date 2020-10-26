package com.example.security.user;

import com.example.security.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    ResponseEntity<String> greetUser() {
        return ResponseEntity.ok()
                             .body("Hello there!");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public User getUserDetails() {
        String email = userService.getCurrentUserEmail();

        return userService.loadUserByUsername(email);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    ResponseEntity<String> getAllUsers() {

        return ResponseEntity.ok()
                             .body("Hello there!");
    }
}
