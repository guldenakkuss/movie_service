package com.example.movie_service.controller;

import com.example.movie_service.model.Movie;
import com.example.movie_service.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController    // Sınıfı bir REST controller olarak işaretler
@RequestMapping("/api/movies") // Tüm endpoint'ler /api/movies ile başlar
@RequiredArgsConstructor       // Service otomatik constructor injection ile gelir
public class MovieController {

    private final MovieService service; // Bağımlılık, Spring tarafından atanır

    /* burada bu kisimlari yapiyoruz CRUD (Create, Read, Update, Delete) -------- */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@Valid @RequestBody Movie m) {
        // Yeni bir film/dizi kaydı ekler
        return service.save(m);
    }

    @GetMapping
    public List<Movie> getAll() {
        // Tüm film/dizi kayıtlarını döndürür
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Movie getOne(@PathVariable Long id) {
        // Tek bir film/dizi kaydını getirir (ID'ye göre)
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @Valid @RequestBody Movie m) {
        // Belirli bir kaydı günceller
        return service.update(id, m);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        // Belirli bir kaydı siler
        service.delete(id);
    }

    /* -------- Filter & Search Endpoint'leri -------- */

    @GetMapping("/filter/rating/{min}")
    public List<Movie> filterByRating(@PathVariable double min) {
        // IMDb puanı min'den büyük/eşit olanları getir
        return service.filterByRating(min);
    }

    @GetMapping("/filter/rating-between")
    public List<Movie> filterByRatingBetween(@RequestParam double min, @RequestParam double max) {
        // IMDb puanı min ve max arasında olanları getir
        return service.filterByRatingBetween(min, max);
    }

    @GetMapping("/filter/type/{type}")
    public List<Movie> filterByType(@PathVariable String type) {
        // Tür'e göre filtrele
        return service.filterByType(type);
    }

    @GetMapping("/filter/director/{director}")
    public List<Movie> filterByDirector(@PathVariable String director) {
        // Yönetmen adına göre filtrele (içeren)
        return service.filterByDirector(director);
    }

    @GetMapping("/filter/writer/{writer}")
    public List<Movie> filterByWriter(@PathVariable String writer) {
        // Writer'a göre filtrele (içeren)
        return service.filterByWriter(writer);
    }

    @GetMapping("/search/title")
    public List<Movie> searchByTitle(@RequestParam String title) {
        // Başlığa göre arama (içeren, case-insensitive)
        return service.searchByTitle(title);
    }

    @GetMapping("/filter/rating-type")
    public List<Movie> filterByRatingAndType(@RequestParam double min, @RequestParam String type) {
        // Hem IMDb puanı hem tür ile filtrele
        return service.filterByRatingAndType(min, type);
    }

    @GetMapping("/filter/rating-director")
    public List<Movie> filterByRatingAndDirector(@RequestParam double min, @RequestParam String director) {
        // Hem IMDb puanı hem yönetmen ile filtrele
        return service.filterByRatingAndDirector(min, director);
    }

    @GetMapping("/search/title-writer")
    public List<Movie> searchByTitleAndWriter(@RequestParam String title, @RequestParam String writer) {
        // Hem başlık hem writer ile arama
        return service.searchByTitleAndWriter(title, writer);
    }

    @GetMapping("/filter/types")
    public List<Movie> filterByTypes(@RequestParam List<String> types) {
        // Birden fazla türle filtrele (örn. movie, series)
        return service.filterByTypes(types);
    }
}
