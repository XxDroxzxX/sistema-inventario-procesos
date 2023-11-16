package com.sistema.inventario.settings;

import com.sistema.inventario.jwtFiler.ToquenFilter;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity

public class SecuritySetting {
    private final AuthenticationProvider authProvider;
    private final  ToquenFilter toquenFilter;
    private Filter ToquenFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().permitAll()
                ).
                sessionManagement(sessonManager ->
                        sessonManager.
                                sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authenticationProvider(authProvider)
                .addFilterBefore(ToquenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}