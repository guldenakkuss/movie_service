package com.example.movie_service.dto;

public record RecMovieDTO(int movieId,
                          String title,
                          String genres,
                          double rating,
                          int count) {}
