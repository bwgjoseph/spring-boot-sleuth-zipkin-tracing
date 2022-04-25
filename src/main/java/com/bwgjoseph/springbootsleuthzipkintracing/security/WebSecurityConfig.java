package com.bwgjoseph.springbootsleuthzipkintracing.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        // disable it otherwise, all request would be forbidden
        http.csrf().disable();

        return http.build();
    }
}
