package com.example.movie_service.dto;
/*
* Bu DTO (Data Transfer Object), öneri sisteminden (yani Python servisten) Java Spring Boot API’na dönecek olan önerilen filmlerin bilgisini taşımak için kullanılır.
Yani kullanıcıya önerilecek her film için bir tane RecMovieDTO oluşturulur.
Öneri sistemi çalıştığında, "sana bu filmi öneriyorum" demek için kullanılır.*/

public record RecMovieDTO(
        int movieId,     // Önerilen filmin ID'si (MovieLens ID veya kendi sisteminde unique id)
        String title,    // Filmin adı (ör. "Interstellar")
        String genres,   // Filmin tür(leri) ("Adventure, Sci-Fi" gibi)
        double rating,   // Filmin ortalama puanı (ör. 8.4)
        int count        // Oylama (veya izlenme, popülerlik) sayısı (ör. 13459)
) {}

