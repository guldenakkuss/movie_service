package com.example.movie_service.services;


import com.example.movie_service.model.Rating;
import com.example.movie_service.model.User;

public interface RatingService {
    Rating rateMovie(User user, Long movieId, double ratingValue);
    // Ek fonksiyonlar: örn. getUserRatings(User user), getAverageRating(Long movieId) vs.
}
