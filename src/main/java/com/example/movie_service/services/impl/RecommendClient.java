package com.example.movie_service.services.impl;

import com.example.movie_service.dto.RatedMovieDTO;
import com.example.movie_service.dto.RecMovieDTO;
import com.example.movie_service.dto.RecommendationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class RecommendClient {
    private final WebClient client;

    public RecommendClient(@Value("${rec.api}") String baseUrl) {
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
    public List<RecMovieDTO> recsByRatedMovies(Long userId, List<RatedMovieDTO> ratedMovies) {
        RecommendationRequest request = new RecommendationRequest(userId,ratedMovies);

        return client.post()
                .uri("/recommend") // Python servisinin endpoint'i
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(RecMovieDTO.class)
                .collectList()
                .block();
    }

}
