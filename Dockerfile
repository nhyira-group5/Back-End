# Estágio de build
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Estágio de produção
FROM openjdk:17
WORKDIR /target

# Copia o JAR gerado do estágio anterior (build) para o diretório /target no container
COPY --from=build /app/target/*.jar /target/api-vitalis-0.0.1-SNAPSHOT.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/target/api-vitalis-0.0.1-SNAPSHOT.jar"]
