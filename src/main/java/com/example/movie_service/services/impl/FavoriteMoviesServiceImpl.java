package com.example.movie_service.services.impl;

import com.example.movie_service.dto.MovieJson;
import com.example.movie_service.model.FavoriteMovies;
import com.example.movie_service.model.Movie;
import com.example.movie_service.model.User;
import com.example.movie_service.repository.FavoriteRepository;
import com.example.movie_service.repository.MovieRepository;
import com.example.movie_service.services.FavoriteMoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor          // constructor-based injection
public class FavoriteMoviesServiceImpl implements FavoriteMoviesService {

    private final FavoriteRepository favoriteRepository;
    private final MovieRepository    movieRepository;

    @Override
    public void save(User user, Long movieId) {    // Integer → Long
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Film bulunamadı"));
        FavoriteMovies favorite = new FavoriteMovies(user, movie);
        favoriteRepository.save(favorite);
    }

    @Override
    public List<MovieJson> getFavorites(User user) {
        return favoriteRepository.findByUser(user).stream()
                .map(fav -> new MovieJson(fav.getMovie()))
                .collect(Collectors.toList());
    }
}
