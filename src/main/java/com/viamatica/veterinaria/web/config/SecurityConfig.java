package com.viamatica.veterinaria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizeRequests -> {
                    customizeRequests
                            .requestMatchers(HttpMethod.GET, "/perfil/**").hasAnyRole("ADMIN", "AUDITOR")
                            .requestMatchers(HttpMethod.POST, "/perfil/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/perfil/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "perfil/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/usuario/**").hasAnyRole("ADMIN", "AUDITOR")
                            .requestMatchers(HttpMethod.POST, "/usuario/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/usuario/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE,"/usuario/**").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();


                })
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

