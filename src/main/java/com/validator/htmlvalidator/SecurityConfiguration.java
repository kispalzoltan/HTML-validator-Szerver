package com.validator.htmlvalidator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/public/**")
                        .permitAll()
                        .requestMatchers("/api/**")
                        //.authenticated()
                        //.anyRequest().
                        .permitAll()
                )
                .csrf(c -> c.disable() )
                .addFilterBefore(new FirebaseTokenFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }
}