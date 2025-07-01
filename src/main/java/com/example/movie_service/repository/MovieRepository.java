package com.example.movie_service.repository;

import com.example.movie_service.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

//Bu arayüz aslında veri iletişim katmanını temsıl eder.
@Repository //Spring bu anotasyon ile interface i bileşen olarak algılayacak
public interface MovieRepository extends JpaRepository<Movie,Long> {
    //Sorgu fonksiyonlarini burada yazicaz JPA method-name query ile Sql yazmadan otomatik sorgular
    //filter ve search sorgularini kullanacagiz

    // imdb puani belli bir degerin uzerinde olanlari getiren sorgu
    List<Movie> findByImdbRatingGreaterThanEqual(double rating);

   //imdb belli bir aralikta filtreliyor bu filtreleme keywordunu kullanicaz service kisminda
    List<Movie> findByImdbRatingBetween(double min, double max);

    //Tür alanı büyük küçük harfe bakmadan eşleşsin
    List<Movie> findByTypeIgnoreCase(String type);

    // 5. Writer’a göre filtrele (içeren, case-insensitive)
    List<Movie> findByWriterContainingIgnoreCase(String writer);

    //Yönetmen adı parametreyi içeriyorsa getir(LIKE %director%)
    List<Movie>findByDirectorContainingIgnoreCase(String director);

    //Başlığı verilen kelimeyi içeren filmler yine büyük mü küçük mü yazıldı umursamasın
     List<Movie>findByTitleContainingIgnoreCase(String title);

    // 7. Hem IMDb puanı hem tür ile
    List<Movie> findByImdbRatingGreaterThanEqualAndTypeIgnoreCase(double rating, String type);

    // 8. Hem IMDb puanı hem yönetmen ile
    List<Movie> findByImdbRatingGreaterThanEqualAndDirectorContainingIgnoreCase(double rating, String director);

    // 10. Birden fazla tür (ör: movie veya series)
    List<Movie> findByTypeIn(List<String> types);
 List<Movie>findByTitleContainingIgnoreCaseAndWriterContainingIgnoreCase(String title,String writer);

 @Query("SELECT m FROM Movie m WHERE m.genre = :genre AND ABS(m.imdbRating - :imdbRating) <= 1 AND m.id <> :movieId")
 List<Movie> findSimilarMovies(@Param("genre") String genre,
                               @Param("imdbRating") double imdbRating,
                               @Param("movieId") Long movieId);


}