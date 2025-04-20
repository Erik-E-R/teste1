# Etapa 1: build da aplicação usando Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY . .

RUN apt-get update && apt-get install -y file
RUN file -i src/main/resources/application.properties

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# Etapa 2: runtime da aplicação
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
