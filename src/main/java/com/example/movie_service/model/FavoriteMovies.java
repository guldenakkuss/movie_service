package com.example.movie_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "favorite_movies",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "movie_id"})
)
@Data                    // getter-setter + equals/hashCode + toString
@NoArgsConstructor       // boş ctor
@AllArgsConstructor      // tüm alanları içeren ctor
public class FavoriteMovies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                         // Integer → Long

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public FavoriteMovies(User user, Movie movie) {
        this.user  = user;
        this.movie = movie;
    }
    // • Lombok @Data sayesinde manuel getter-setter yazmaya gerek kalmadı.
    // • Eğer Lombok kullanmak istemiyorsan, eski getter-setter’ları
    //   Long tipine güncelleyerek bırakabilirsin; işlev aynı.
}
