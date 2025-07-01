package com.example.movie_service.controller;

import com.example.movie_service.model.Movie;
import com.example.movie_service.repository.MovieRepository;
import com.example.movie_service.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
public class RecommendationController {
 private final MovieRepository movieRepository;

    @GetMapping("/{movieId}")
    public List<Movie> recommendMovies(@PathVariable Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return movieRepository.findSimilarMovies(
                movie.getGenre(),
                (double) movie.getImdbRating(),
                movie.getId()
        );
}}
