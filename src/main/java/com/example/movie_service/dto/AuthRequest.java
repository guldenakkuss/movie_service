package com.example.movie_service.dto;

// src/main/java/com/example/movie_service/dto/AuthRequest.java
import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
