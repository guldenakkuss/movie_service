# Movie Service API

Java Spring Boot ile geliştirilmiş, MySQL veritabanı kullanan ve Docker ile container olarak çalışan bir film/dizi arşiv API servisidir.

## Özellikler

- Film/dizi ekleme, listeleme, güncelleme, silme (CRUD)
- IMDB puanına, türüne, yönetmen/yazara veya başlığa göre filtreleme & arama endpoint'leri
- RESTful API mimarisi
- Docker Compose ile kolay kurulum
- MySQL veritabanı entegrasyonu

## Kurulum

### 1. Projeyi Klonlayın

```bash
git clone https://github.com/guldenakkuss/movie_service.git
cd movie_service
 
 TEMEL API ENDPOINTLERI
| Yöntem | URL                                    | Açıklama                                         |
| ------ | -------------------------------------- | ------------------------------------------------ |
| GET    | /api/movies                            | Tüm film/dizi kayıtlarını getirir                |
| POST   | /api/movies                            | Yeni film/dizi kaydı ekler                       |
| GET    | /api/movies/{id}                       | ID ile tek bir kayıt getirir                     |
| PUT    | /api/movies/{id}                       | Kaydı günceller                                  |
| DELETE | /api/movies/{id}                       | Kaydı siler                                      |
| GET    | /api/movies/filter/rating/{min}        | IMDb puanı min ve üstü kayıtları getirir         |
| GET    | /api/movies/filter/rating-between      | min ve max arasında kayıtları getirir            |
| GET    | /api/movies/filter/type/{type}         | Türe göre filtreler                              |
| GET    | /api/movies/filter/director/{director} | Yönetmen adına göre filtreler                    |
| GET    | /api/movies/filter/writer/{writer}     | Yazar adına göre filtreler                       |
| GET    | /api/movies/search/title?title=...     | Başlığa göre arama                               |
| GET    | /api/movies/filter/rating-type         | IMDb puanı ve tür ile filtrele                   |
| GET    | /api/movies/filter/rating-director     | IMDb puanı ve yönetmen ile filtrele              |
| GET    | /api/movies/search/title-writer        | Başlık ve yazar ile arama                        |
| GET    | /api/movies/filter/types               | Birden fazla türle filtrele (örn: movie, series) |

Kullanılan Teknolojiler
Java 17
Spring Boot 3.x
Spring Data JPA & Hibernate
Lombok
MySQL 8
Docker & Docker Compose

Hazırlayan:
Gülden Akkuş