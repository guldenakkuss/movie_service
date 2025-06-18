# Java 17 tabanlı bir imajdan başla
FROM openjdk:17-jdk-alpine

# JAR dosyasını kopyala (target klasöründen)
COPY target/*.jar app.jar

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar"]
