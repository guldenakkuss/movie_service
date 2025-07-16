package com.example.movie_service.controller;

import com.example.movie_service.dto.RatedMovieDTO;
import com.example.movie_service.dto.RecMovieDTO;
import com.example.movie_service.model.User;
import com.example.movie_service.model.Rating;
import com.example.movie_service.repository.RatingRepository;
import com.example.movie_service.repository.UserRepository;
import com.example.movie_service.services.impl.RecommendClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
public class RecommendationController {

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final RecommendClient recommendClient;

    @PostMapping
    public List<RecMovieDTO> recommendBasedOnRatings(@AuthenticationPrincipal UserDetails userDetails) {
        // 1. Kullanıcıyı bul
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Kullanıcının verdiği puanları çek
        List<Rating> userRatings = ratingRepository.findByUser(user);

        // 3. DTO'ya dönüştür
        List<RatedMovieDTO> ratedMovies = userRatings.stream()
                .map(r -> new RatedMovieDTO(
                        r.getMovie().getId(),
                        r.getMovie().getTitle(),
                        r.getMovie().getGenres(),
                        r.getMovie().getImdbID(),
                        r.getRating()
                ))
                .toList();

        // 4. Python recommendation API'ına isteği gönder
        return recommendClient.recsByRatedMovies(user.getId(),ratedMovies);
    }
}
