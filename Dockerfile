FROM openjdk:17
WORKDIR /target  # Define o diretório de trabalho como "target"

# Copia o JAR diretamente do diretório local para o diretório de trabalho do contêiner
COPY target/api-vitalis-0.0.1-SNAPSHOT.jar /target/api-vitalis-0.0.1-SNAPSHOT.jar

# Comando para rodar a aplicação Java
ENTRYPOINT ["java", "-jar", "/target/api-vitalis-0.0.1-SNAPSHOT.jar"]
