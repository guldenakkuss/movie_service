package com.example.movie_service.dto;

import com.example.movie_service.model.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MovieJson {
    @Setter
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Awards")
    private String awards;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("Metascore")
    private String metascore;
    @JsonProperty("imdbRating")
    private Double imdbRating;
    @JsonProperty("imdbVotes")
    private String imdbVotes;
    @JsonProperty("imdbID")
    private String imdbID;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("totalSeasons")
    private String totalSeasons;
    @JsonProperty("ComingSoon")
    private Boolean ComingSoon;


    // Getter ve setter'ları ekle
    @Setter
    private Long id;
    @Setter
    private String genres;

    public MovieJson() {} // Boş constructor (gerekliyse Jackson için)

    public MovieJson(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.genres = movie.getGenres();
        this.actors=movie.getActors();
        this.director=movie.getDirector();
        this.writer=movie.getWriter();
        this.language=movie.getLanguage();
        this.country=movie.getCountry();
        this.awards=movie.getAwards();
        this.plot=movie.getPlot();
        this.runtime=movie.getRuntime();
        this.imdbID=movie.getImdbID();
        this.type=movie.getType();
        this.totalSeasons=movie.getTotalSeasons();
        this.ComingSoon=movie.getComingSoon();
        this.year=movie.getYear();
        this.rated=movie.getRated();
        this.metascore=movie.getMetascore();
        this.imdbVotes=movie.getImdbVotes();
        this.totalSeasons=movie.getTotalSeasons();
        this.poster=movie.getPoster();
        this.language=movie.getLanguage();
        this.actors=movie.getActors();
        this.released=movie.getReleased();
        this.imdbRating=movie.getImdbRating();


    }

}
