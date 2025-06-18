package com.example.movie_service.services;

import com.example.movie_service.model.Movie;

import java.util.List;
//interface kisminda sadece degisken veya metodun ismini yaziyoruz uygulama kismi hangi metodun ne yaptigi implementation kisminda olacak/
public interface MovieService {
    //CRUD kismini buraya yazalim
    Movie save(Movie m); //Movie türü return eden save fonksıyonu ve içindeki parametre adı verilmiş bu fonksiyonun içinin dolu halini implementatıonda yapacagız
    Movie update (Long id,Movie m);
    void delete(Long id);
    List<Movie> listAll();
    Movie get(Long id);

    // Filtre ve arama (repository’deki sorgulara karşılık gelenler)
    List<Movie> filterByRating(double min);
    List<Movie> filterByRatingBetween(double min, double max);
    List<Movie> filterByType(String type);
    List<Movie> filterByDirector(String director);
    List<Movie> filterByWriter(String writer);
    List<Movie> searchByTitle(String title);
    List<Movie> filterByRatingAndType(double min, String type);
    List<Movie> filterByRatingAndDirector(double min, String director);
    List<Movie> searchByTitleAndWriter(String title, String writer);
    List<Movie> filterByTypes(List<String> types);
    List<Movie>searchByTitleContainingIgnoreCaseAndWriterContainingIgnoreCase(String title,String writer);

    /*
     * Her bir metot, bir iş ihtiyacına karşılık gelir.
     * Controller bu arayüzü çağırır; implementation’ın nasıl çalıştığını bilmez.
     */

}

