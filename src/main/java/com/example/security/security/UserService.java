package com.example.security.security;

import com.example.security.user.User;
import com.example.security.user.UserModel;
import com.example.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) {

        UserModel userModel = userRepository.findByEmail(email)
                                            .orElseThrow(() -> new UsernameNotFoundException(
                                                "User Not Found with email : " + email));

        return toDomain(userModel);
    }

    public String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("Could not find current user!");
        }
    }

    private User toDomain(UserModel userModel) {
        User u = new User();
        u.setEmail(userModel.getEmail());
        u.setId(userModel.getId());
        u.setPassword(userModel.getPassword());
        u.setRole(userModel.getRole()
                           .getName());
        u.setName(userModel.getName());

        return u;
    }
}
