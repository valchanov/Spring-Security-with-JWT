package com.example.security.base;

import com.example.security.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

//Needed for @CreatedBy and @LastModifiedBy
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        if (SecurityContextHolder.getContext()
                                 .getAuthentication() != null
            && !SecurityContextHolder.getContext()
                                     .getAuthentication()
                                     .getAuthorities()
                                     .contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
            User principal = (User) SecurityContextHolder.getContext()
                                                         .getAuthentication();
            return Optional.of(principal.getUserId());
        }

        return Optional.empty();
    }
}