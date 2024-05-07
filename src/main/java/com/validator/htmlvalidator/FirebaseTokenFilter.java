package com.validator.htmlvalidator;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FirebaseTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = request.getHeader("Authorization");
        System.out.println(request.getRequestURL().toString().contains("public"));
        if(!(request.getRequestURL().toString().contains("public"))){
        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            System.err.println("Not start with Bearer");
            return;
        }

        FirebaseToken decodedToken = null;
        try {
            String token = authenticationHeader.substring(7, authenticationHeader.length());
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            System.err.println("Token: "+token);
            System.err.println("decoded token "+decodedToken.getEmail());
        } catch (FirebaseAuthException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            System.err.println("Exception");
            System.err.println(e.toString());
            return;
        }

        if (decodedToken == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            System.err.println("decodedtoken is null");
            return;
        }}

        filterChain.doFilter(request, response);
    }
}