package com.example.movie_service.controller;
import com.example.movie_service.repository.UserRepository;
import com.example.movie_service.services.RatingService;
import com.example.movie_service.model.Rating;
import com.example.movie_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/ratings")
@RequiredArgsConstructor
@RestController
public class RatingController {
    private final RatingService ratingService;
    private final UserRepository userRepository;

    @PostMapping("/{movieId}")
    public ResponseEntity<?> rateMovie(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long movieId,
            @RequestParam double rating) {


        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rating savedRating = ratingService.rateMovie(user, movieId, rating);
        return ResponseEntity.ok(savedRating);
    }
}
