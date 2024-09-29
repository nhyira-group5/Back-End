FROM openjdk:17
WORKDIR /target
COPY target/api-vitalis-0.0.1-SNAPSHOT.jar /target/api-vitalis-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/target/api-vitalis-0.0.1-SNAPSHOT.jar"]
