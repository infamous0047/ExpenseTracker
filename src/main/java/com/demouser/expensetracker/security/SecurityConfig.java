package com.demouser.expensetracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth ->
            auth.requestMatchers(org.springframework.http.HttpMethod.POST, "/api/v1/users").permitAll()
                .anyRequest().authenticated()
        )
        .httpBasic(basic -> basic
            .authenticationEntryPoint((request, response, authException) -> {
                if (request.getRequestURI().equals("/api/v1/users")
                    && request.getMethod().equals("POST")) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setHeader("WWW-Authenticate", "Basic");
                }
            })
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
