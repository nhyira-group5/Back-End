# Usando a imagem do OpenJDK como base
FROM openjdk:17 AS build

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Instalando o Maven
RUN apt-get update && apt-get install -y maven

# Copiando o código fonte
COPY src ./src

# Construindo o projeto
RUN mvn clean package -DskipTests

# Definindo a imagem final
FROM openjdk:17

# Definindo o diretório de trabalho
WORKDIR /target

# Copiando o artefato gerado da etapa de build
COPY --from=build target/api-vitalis.jar api-vitalis.jar

# Definindo o ponto de entrada da aplicação
ENTRYPOINT ["java", "-jar", "api-vitalis.jar"]
