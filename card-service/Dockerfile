FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/card-service-0.0.1-SNAPSHOT.jar card-service.jar
EXPOSE 8080
CMD ["java", "-jar", "card-service.jar"]