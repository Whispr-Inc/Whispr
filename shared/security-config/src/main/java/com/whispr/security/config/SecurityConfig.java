package com.whispr.security.config;

import com.whispr.security.interfaces.SecurityCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        List<SecurityCustomizer> securityCustomizers,
        Converter<Jwt, AbstractAuthenticationToken> tokenConverter
    ) throws Exception {

        // Ensure that at least one security customizer is present
        if (securityCustomizers.isEmpty()) {
            securityCustomizers.add(defaultSecurityCustomizer());
        }

        // Configure HttpSecurity
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req -> {
                // Apply custom security customizers
                for (SecurityCustomizer customizer : securityCustomizers) {
                    customizer.customize(req);
                }
            })
            .oauth2ResourceServer(auth -> auth
                .jwt(token -> token.jwtAuthenticationConverter(tokenConverter))
            );

        return http.build();
    }

    @Bean
    public SecurityCustomizer defaultSecurityCustomizer() {
        return (registry) -> {
            registry.anyRequest().authenticated();
        };
    }

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> keycloakJwtAuthenticationConverter() {
        return source -> new JwtAuthenticationToken(
            source,
            Stream.concat(
                new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                extractResourceRoles(source).stream()
            ).collect(Collectors.toSet())
        );
    }

    @SuppressWarnings("unchecked")
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        var resourceAccess = new HashMap<>(jwt.getClaimAsMap("resource_access"));

        var eternal = (Map<String, List<String>>) resourceAccess.get("account");
        var roles = eternal.get("roles");

        return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace("-", "_")))
            .collect(Collectors.toSet());
    }
}
