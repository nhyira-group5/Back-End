# Usando uma imagem com Maven pré-instalado
FROM maven:3.8.5-openjdk-17 AS build

# Define o diretório de trabalho
WORKDIR /target

# Copia o arquivo pom.xml e o diretório src
COPY pom.xml .
COPY src ./src

# Executa o Maven para compilar o projeto e criar o JAR
RUN mvn clean package -DskipTests

# Cria a imagem final com o JAR
FROM openjdk:17-jdk-slim
WORKDIR /target
COPY --from=build /target/*.jar /target/api-vitalis.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/target/api-vitalis.jar"]
