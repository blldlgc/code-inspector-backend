# Build stage
FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Maven repository'den JUnit ve JaCoCo JAR'larını kopyala
COPY --from=build /root/.m2/repository/junit/ /app/libs/junit/
COPY --from=build /root/.m2/repository/org/jacoco/ /app/libs/jacoco/

# Simian klasörünü oluştur
RUN mkdir -p /app/libs/simian-4.0.0

# Simian jar'ını kopyala
COPY src/main/resources/libs/simian-4.0.0/simian-4.0.0.jar /app/libs/simian-4.0.0/

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]