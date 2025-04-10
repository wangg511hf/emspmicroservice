FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/account-service-0.0.1-SNAPSHOT.jar account-service.jar
EXPOSE 8080
CMD ["java", "-jar", "account-service.jar"]