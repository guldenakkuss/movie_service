package com.example.movie_service.services.impl;

import com.example.movie_service.model.Movie;
import com.example.movie_service.model.Rating;
import com.example.movie_service.model.User;
import com.example.movie_service.repository.MovieRepository;
import com.example.movie_service.repository.RatingRepository;
import com.example.movie_service.repository.UserRepository;
import com.example.movie_service.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Override
    public Rating rateMovie(User user, Long movieId, double ratingValue) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Optional<Rating> optionalRating = ratingRepository.findByUserAndMovie(user, movie);

        Rating rating;
        if (optionalRating.isPresent()) {
            // Daha önce puan verilmiş, güncelle
            rating = optionalRating.get();
            rating.setRating(ratingValue);
        } else {
            // İlk defa puan veriliyor
            rating = new Rating(user, movie, ratingValue);
        }
        return ratingRepository.save(rating);
    }
}
