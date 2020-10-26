package com.example.security.security;

import com.example.security.role.RoleModel;
import com.example.security.user.UserModel;
import com.example.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.example.security.role.UserRole.ROLE_USER;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LogInRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()));

        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByName(registerRequest.getName())) {
            return ResponseEntity.badRequest()
                                 .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest()
                                 .body("Error: Email is already in use!");
        }

        registerRequest.setRole(ROLE_USER);
        UserModel userModel = toModel(registerRequest);

        userRepository.save(userModel);

        return ResponseEntity.ok("User registered successfully!");
    }

    private UserModel toModel(RegisterRequest registerRequest) {
        UserModel userModel = new UserModel();
        userModel.setName(registerRequest.getName());
        userModel.setEmail(registerRequest.getEmail());
        userModel.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userModel.setRole(new RoleModel(registerRequest.getRole()));

        return userModel;
    }
}