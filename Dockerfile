# Usar uma imagem com Maven para construir o projeto
FROM maven:3.8.5-openjdk-17 AS build

# Define o diretório de trabalho no container
WORKDIR /target

# Copia o arquivo pom.xml e o diretório src para o container
COPY pom.xml .
COPY src ./src

# Executa o Maven para compilar o projeto e gerar o JAR
RUN mvn clean package -DskipTests

# Usar uma imagem mais leve do Java para rodar o JAR
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no container como /target
WORKDIR /target

# Copia o JAR gerado do estágio anterior (build) para o diretório /target no container
COPY --from=build /target/api-vitalis-0.0.1-SNAPSHOT.jar /target/api-vitalis-0.0.1-SNAPSHOT.jar

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "/target/api-vitalis-0.0.1-SNAPSHOT.jar"]
