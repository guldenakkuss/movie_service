package com.example.movie_service.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class RatedMovieDTO {

        @JsonProperty("movieId")
        private Long movieId;

        @JsonProperty("title")
        private String title;

        @JsonProperty("genres")
        private String genres;

        @JsonProperty("imdbId")
        private String imdbId;

        @JsonProperty("rating")
        private double rating;   // Kullanıcının verdiği puan
    }


