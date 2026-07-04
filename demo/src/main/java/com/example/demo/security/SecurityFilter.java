package com.example.demo.security;

import com.example.demo.models.entities.User;
import com.example.demo.services.TokenService;
import com.example.demo.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (token != null && tokenService.validateToken(token)) {
            User user = resolveUser(token);
            if (user != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private User resolveUser(String token) {
        try {
            String email = tokenService.getEmail(token);
            if (hasText(email)) {
                return userService.getUserByEmail(email);
            }

            String subject = tokenService.getSubject(token);
            if (hasText(subject)) {
                return userService.getUserById(Long.parseLong(subject));
            }
        } catch (RuntimeException ignored) {
            SecurityContextHolder.clearContext();
        }
        return null;
    }

    private String getToken(@NonNull HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
