package com.example.movie_service.repository;

import com.example.movie_service.model.Movie;
import com.example.movie_service.model.Rating;
import com.example.movie_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserAndMovie(User user, Movie movie);
    List<Rating> findByUser(User user);
    List<Rating> findByMovie(Movie movie);
}
