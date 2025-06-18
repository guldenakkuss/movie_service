package com.example.movie_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "movies")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @Column(name = "movie_year")//burada year Sql de reserved
    private String year;

    private String rated;
    private String released;
    private String runtime;
    private String genre;

    private Boolean ComingSoon;

    @NotBlank(message = "Director is required")
    private String director;

    @NotBlank(message = "Writer is required")
    private String writer;

    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metascore;

    @Min(0) @Max(10)
    private double imdbRating;

    private String imdbVotes;
    private String imdbID;

    @NotBlank(message = "Type is required")
    private String type;

    // Bazı dizilerde bulunan ekstra alan
    private String totalSeasons;
}
