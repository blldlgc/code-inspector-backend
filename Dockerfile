# Build stage
FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Uygulama portunu açıyoruz (varsayılan Spring Boot portu)
EXPOSE 8080

# Uygulamayı çalıştırma komutu
ENTRYPOINT ["java", "-jar", "app.jar"] 