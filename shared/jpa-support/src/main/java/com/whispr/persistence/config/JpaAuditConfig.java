package com.whispr.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", modifyOnCreate = true)
public class JpaAuditConfig {

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        // TODO: Here you can implement logic to return the current auditor.
        // For example, you might fetch the current user's ID from the security context.
        // Returning null for now, which means no auditor is set.

        return Optional::empty;
    }
}
