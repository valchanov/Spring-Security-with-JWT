package com.example.security.config;

import com.example.security.base.SpringSecurityAuditorAware;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZonedDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
class AppConfig {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }

    // Makes ZonedDateTime compatible with auditing fields
    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}