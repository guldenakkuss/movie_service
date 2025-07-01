package com.example.movie_service.services;


import com.example.movie_service.model.User;

public interface AuthService {
    User register(String username, String password);    // Kullanıcı kayıt işlemi
    String login(String username, String password);       // Kullanıcı giriş işlemi
}
