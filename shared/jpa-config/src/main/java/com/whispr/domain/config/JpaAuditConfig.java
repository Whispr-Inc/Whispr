package com.whispr.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing(
    auditorAwareRef = "auditorAware",
    modifyOnCreate = true               // Update updated timestamp also on during creation of an entity
)
public class JpaAuditConfig {

    @Bean
    public AuditorAware<UUID> auditorAware() {
        // TODO: Implement a proper AuditorAware that retrieves the current user
        return Optional::empty;
    }
}
