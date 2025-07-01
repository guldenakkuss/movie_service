package com.example.movie_service.services;

import com.example.movie_service.dto.MovieJson;
import com.example.movie_service.model.User;

import java.util.List;

public interface FavoriteMoviesService {

    void save(User user, Long movieId);          // Integer → Long

    List<MovieJson> getFavorites(User user);
}
