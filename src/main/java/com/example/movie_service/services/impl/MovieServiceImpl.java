package com.example.movie_service.services.impl;

import org.springframework.stereotype.Service;
import com.example.movie_service.model.Movie;
import com.example.movie_service.repository.MovieRepository;
import com.example.movie_service.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor   // repo'yu constructor ile otomatik enjekte eder
@Transactional            // Bütün işlemler transaction içinde yürür (DB güvenliği)
 class MovieServiceImpl implements MovieService {

    private final MovieRepository repo;

    @Override
    public Movie save(Movie m) {
        return repo.save(m);
    }

    @Override
    public Movie update(Long id, Movie m) {
        Movie db = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));//database e gidiyor filmin id sine bakarak filmi buluyor findById(id) eger ki o id de film yoksa exception donuyor
        db.setTitle(m.getTitle());
        db.setDirector(m.getDirector());
        db.setWriter(m.getWriter());
        db.setImdbRating(m.getImdbRating());
        db.setType(m.getType());
        return repo.save(db);
    }
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Movie> listAll() {
        return repo.findAll();
    }

    @Override
    public Movie get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    // ---- Filtre ve arama ----

    @Override
    public List<Movie> filterByRating(double min) {
        return repo.findByImdbRatingGreaterThanEqual(min);
    }

    @Override
    public List<Movie> filterByRatingBetween(double min, double max) {
        return repo.findByImdbRatingBetween(min, max);
    }

    @Override
    public List<Movie> filterByType(String type) {
        return repo.findByTypeIgnoreCase(type);
    }

    @Override
    public List<Movie> filterByDirector(String director) {
        return repo.findByDirectorContainingIgnoreCase(director);
    }

    @Override
    public List<Movie> filterByWriter(String writer) {
        return repo.findByWriterContainingIgnoreCase(writer);
    }

    @Override
    public List<Movie> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Movie> filterByRatingAndType(double min, String type) {
        return repo.findByImdbRatingGreaterThanEqualAndTypeIgnoreCase(min, type);
    }

    @Override
    public List<Movie> filterByRatingAndDirector(double min, String director) {
        return repo.findByImdbRatingGreaterThanEqualAndDirectorContainingIgnoreCase(min, director);
    }

    @Override
    public List<Movie> searchByTitleAndWriter(String title, String writer) {
        return repo.findByTitleContainingIgnoreCaseAndWriterContainingIgnoreCase(title, writer);
    }

    @Override
    public List<Movie> filterByTypes(List<String> types) {
        return repo.findByTypeIn(types);
    }

    @Override
    public List<Movie> searchByTitleContainingIgnoreCaseAndWriterContainingIgnoreCase(String title, String writer) {
        return List.of();
    }
}
