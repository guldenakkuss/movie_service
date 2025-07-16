package com.example.movie_service.dto;

import java.util.List;

public record RecommendationRequest(Long userId, List<RatedMovieDTO> ratedMovie) {
}