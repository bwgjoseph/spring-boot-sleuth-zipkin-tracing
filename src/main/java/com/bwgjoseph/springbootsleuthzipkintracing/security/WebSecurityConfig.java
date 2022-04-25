package com.bwgjoseph.springbootsleuthzipkintracing.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.ListeningSecurityContextHolderStrategy;
import org.springframework.security.core.context.SecurityContextChangedListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    public WebSecurityConfig(List<SecurityContextChangedListener> securityContextChangedListeners) {
		SecurityContextHolderStrategy strategy = new ListeningSecurityContextHolderStrategy(
				SecurityContextHolder.getContextHolderStrategy(), securityContextChangedListeners);
		SecurityContextHolder.setContextHolderStrategy(strategy);
	}

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        // disable it otherwise, all request would be forbidden
        http.csrf().disable();

        // ensure only authenticated user can make api request
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        // support basic authentication
        http.httpBasic();

        return http.build();
    }
}
