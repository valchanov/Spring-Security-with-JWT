package com.example.security.security;

import com.example.security.role.RoleModel;
import com.example.security.role.RoleRepository;
import com.example.security.user.UserModel;
import com.example.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
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

        //set ROLE_USER
        registerRequest.setRole_id(2L);
        UserModel userModel = toEntity(registerRequest);

        userRepository.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("User registered successfully!");
    }

    private UserModel toEntity(RegisterRequest request) {
        UserModel userModel = new UserModel();
        userModel.setName(request.getName());
        userModel.setEmail(request.getEmail());
        userModel.setPassword(passwordEncoder.encode(request.getPassword()));
        userModel.setRole(getRoleModel(request));

        return userModel;
    }

    private RoleModel getRoleModel(RegisterRequest registerRequest) {
        Long roleId = registerRequest.getRole_id();

        return roleRepository.findById(roleId)
                             .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}