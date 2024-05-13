FROM ubuntu:latest AS build

# Atualiza e instala o JDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

# Copia o código-fonte
COPY . .

# Instala o Maven e compila o projeto
RUN apt-get install -y maven && \
    mvn clean install && \
    rm -rf /root/.m2 /var/lib/apt/lists/*

# Inicia a segunda fase
FROM openjdk:17-jdk-slim

# Expõe a porta 8080
EXPOSE 8080

# Copia o arquivo JAR da fase de construção
COPY --from=build /target/perdidoseachados-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
