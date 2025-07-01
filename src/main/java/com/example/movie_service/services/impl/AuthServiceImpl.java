package com.example.movie_service.services.impl;

import com.example.movie_service.model.User;
import com.example.movie_service.repository.UserRepository;

import com.example.movie_service.security.JwtUtil;
import com.example.movie_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Collections;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Inject et!
    private final JwtUtil jwtUtil;

    @Override
    public User register(String username, String password) {
        Optional<User> existing = userRepository.findByUsername(username);
        if (existing.isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Şifre hashleniyor!
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) { // Hash karşılaştırması!
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),user.getPassword(), Collections.emptyList())
        );
    }
}
