package com.example.movie_service.services.impl;

import com.example.movie_service.dto.RecMovieDTO;
import lombok.RequiredArgsConstructor;
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

    public List<RecMovieDTO> recs(long userId, int n, int minVotes) {
        return client.get()
                .uri(uri -> uri.path("/recommend")
                        .queryParam("user_id",  userId)
                        .queryParam("top_n",    n)
                        .queryParam("min_votes",minVotes)
                        .build())
                .retrieve()
                .bodyToFlux(RecMovieDTO.class)
                .collectList()
                .block();            //   demo: bloklu
    }
}
