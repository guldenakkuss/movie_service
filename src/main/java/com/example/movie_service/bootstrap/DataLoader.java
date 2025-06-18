package com.example.movie_service.bootstrap;

import com.example.movie_service.dto.MovieJson;
import com.example.movie_service.model.Movie;
import com.example.movie_service.services.MovieService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.List;

@Component // Spring bu sınıfı otomatik olarak bileşen (bean) olarak tanır.
@RequiredArgsConstructor // Lombok: final değişkenler için constructor oluşturur (dependency injection kolaylaşır)
@Slf4j // Lombok: Otomatik log nesnesi ekler (log.info, log.error kullanabilirsin)
public class DataLoader {
    private final MovieService service; // Filmleri kaydetmek için servis katmanı kullanılır.
    private final ObjectMapper mapper = new ObjectMapper(); // JSON -> Java objesi dönüşümü için Jackson kullanılır.

    @PostConstruct // Spring bean oluşturduktan sonra bu metodu otomatik çağırır (uygulama başında çalışır)
    public void initDatabase() {
        if (service.listAll().size() > 0) {
            log.info("Veritabanında zaten veri var, tekrar yüklenmedi.");
            return;
        }
        try (
                // movies.json dosyasını resources klasöründen input stream olarak açıyoruz
                InputStream is = getClass().getResourceAsStream("/movies.json")
        ) {
            // JSON dosyasındaki verileri MovieJson tipinde bir listeye çeviriyoruz
            List<MovieJson> raw = mapper.readValue(is, new TypeReference<>() {});

            // Her MovieJson nesnesini Movie entity'ye çeviriyoruz ve veritabanına kaydediyoruz
            raw.stream()
                    .map(this::convert) // MovieJson -> Movie
                    .forEach(service::save); // veritabanına ekle

            // Kaç kayıt yüklendiğini logla
            log.info("Initial movie data loaded: {}", raw.size());
        } catch (Exception e) {
            // Herhangi bir hata olursa hatayı logla
            log.error("Failed to load initial data", e);
        }
    }

    // MovieJson nesnesini gerçek Movie entity'ye dönüştürür
    private Movie convert(MovieJson j) {
        return Movie.builder()
                .title(j.getTitle())
                .year(j.getYear())
                .rated(j.getRated())
                .released(j.getReleased())
                .runtime(j.getRuntime())
                .genre(j.getGenre())
                .director(j.getDirector())
                .writer(j.getWriter())
                .actors(j.getActors())
                .plot(j.getPlot())
                .language(j.getLanguage())
                .country(j.getCountry())
                .awards(j.getAwards())
                .poster(j.getPoster())
                .metascore(j.getMetascore())
                .imdbRating(parseDouble(j.getImdbRating()))
                .imdbVotes(j.getImdbVotes())
                .imdbID(j.getImdbID())
                .type(j.getType())
                .totalSeasons(j.getTotalSeasons())
                .build();
    }


    // String ifadeyi güvenli şekilde double'a çevirir, hata olursa 0.0 döner
    private double parseDouble(String s){
        try {
            return Double.parseDouble(s);
        } catch (Exception e){
            return 0.0;
        }
    }
}
