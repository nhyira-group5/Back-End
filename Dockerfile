# Etapa 1: Usar Maven para construir o .jar
FROM maven:3.8.6-openjdk-17 AS build

# Definir o diretório de trabalho no container
WORKDIR /app

# Copiar o arquivo pom.xml e as dependências para o container
COPY pom.xml .
COPY src ./src

# Rodar o Maven para gerar o .jar
RUN mvn clean package -DskipTests

# Etapa 2: Usar o OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim

# Diretório de trabalho na imagem final
WORKDIR /app

# Copiar o arquivo .jar gerado na etapa de build
COPY --from=build /app/target/api-vitalis-0.0.1-SNAPSHOT.jar /app/api-vitalis.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "api-vitalis.jar"]
