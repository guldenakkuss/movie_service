package com.example.movie_service.controller;

import com.example.movie_service.dto.AuthRequest;
import com.example.movie_service.model.User;
import com.example.movie_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody AuthRequest authRequest) {
        return authService.register(authRequest.getUsername(), authRequest.getPassword());
    }

    public record TokenDto(String token) {}
    @PostMapping("/login")
    public TokenDto login(@RequestBody AuthRequest req) {
        String jwt = authService.login(req.getUsername(), req.getPassword());
        return new TokenDto(jwt);
    }
}
