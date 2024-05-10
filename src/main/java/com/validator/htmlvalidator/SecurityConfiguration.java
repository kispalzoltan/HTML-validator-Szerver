package com.validator.htmlvalidator;


import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final FirebaseAuth firebaseAuth;
    @Autowired
    public SecurityConfiguration(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

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
                .addFilterBefore(new FirebaseTokenFilter(firebaseAuth), BasicAuthenticationFilter.class);

        return http.build();
    }
}