package com.example.movie_service.controller;

import com.example.movie_service.dto.MovieJson;
import com.example.movie_service.model.User;
import com.example.movie_service.repository.UserRepository;
import com.example.movie_service.services.FavoriteMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteMoviesController {

    @Autowired
    private FavoriteMoviesService favoriteMoviesService;

    @Autowired
    private UserRepository userRepository;

    // Kullanıcı bir filmi favorilere ekler
    @PostMapping("/{movieId}")
    public ResponseEntity<?> addFavorite(@PathVariable Long movieId,
                                         @AuthenticationPrincipal UserDetails user){
        User userEntity=userRepository.findByUsername(user.getUsername()).get();
        favoriteMoviesService.save(userEntity, movieId);
        return ResponseEntity.ok().build();
    }

    // Kullanıcının favori filmlerini getirir
    @GetMapping
    public List<MovieJson> getFavorites(@AuthenticationPrincipal UserDetails user) {
        User userEntity=userRepository.findByUsername(user.getUsername()).orElseThrow();
        return favoriteMoviesService.getFavorites(userEntity);
    }
}
