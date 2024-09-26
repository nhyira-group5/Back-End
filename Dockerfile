FROM openjdk:17
WORKDIR /app
COPY API/target/api-vitalis-0.0.1-SNAPSHOT.jar /app/api-vitalis-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "api-vitalis-0.0.1-SNAPSHOT.jar"]
