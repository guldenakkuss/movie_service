package com.example.movie_service.controller;

import com.example.movie_service.dto.RecMovieDTO;
import com.example.movie_service.services.impl.RecommendClient;
import lombok.RequiredArgsConstructor;
import com.example.movie_service.model.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class PersonalizedRecController {

    private final RecommendClient client;
    @GetMapping("/{id}/recommendations")
    public List<RecMovieDTO> userRecs(@PathVariable long id,
                                      @RequestParam(defaultValue = "5")  int n,
                                      @RequestParam(defaultValue = "50") int minVotes,
                                      @AuthenticationPrincipal User user) {

        // İsteğe bağlı güvenlik kontrolü
        if (user != null && user.getId() != id)
            throw new RuntimeException("Forbidden");

        return client.recs(id, n, minVotes);
    }
}
