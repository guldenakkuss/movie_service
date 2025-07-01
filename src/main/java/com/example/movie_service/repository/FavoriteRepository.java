package com.example.movie_service.repository;

import com.example.movie_service.model.FavoriteMovies;
import com.example.movie_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteMovies, Long> {

    List<FavoriteMovies> findByUser(User user);
}
