package com.huisa.security;

import com.huisa.security.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@EnableWebFluxSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(auth -> auth
                        .pathMatchers(
                                HttpMethod.POST,
                                "auth-service/roles/**",
                                "auth-service/user-roles"
                        ).hasRole("ADMIN")
                        .pathMatchers(
                                HttpMethod.PUT,
                                "auth-service/roles/**",
                                "auth-service/user-roles"
                        ).hasRole("ADMIN")
                        .pathMatchers(
                                HttpMethod.DELETE,
                                "auth-service/roles/**",
                                "auth-service/user-roles"
                        ).hasRole("ADMIN")
                        .pathMatchers(
                                "/diary-service/diary-entries",
                                "/auth-service/auth/**",
                                "/country-service/countries",
                                "/currency-service/currencies",
                                "/public/**"
                        ).permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtDecoder(jwtDecoder())
                        )
                )
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(
                JwtUtils.SECRET_KEY.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        return NimbusReactiveJwtDecoder.withSecretKey(secretKeySpec).build();
    }
}