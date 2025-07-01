package com.example.movie_service.config;

import com.example.movie_service.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

     public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
         this.jwtAuthFilter=jwtAuthFilter;
     }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // Kayıt ve giriş herkes erişebilir
                        .requestMatchers(HttpMethod.GET, "/api/movies/**", "/api/recommend/**").permitAll()
                        .anyRequest().authenticated());

                http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // JWT filter’ı ekle
        return http.build();
    }

    // AuthenticationManager bean’i JWT authentication için gerekir
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
