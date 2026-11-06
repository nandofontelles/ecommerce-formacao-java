#Stage 1: Build (compilacao)
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

#Diretorio de trabalho dentro do container
WORKDIR /app

#Copiar o arquivo pom.xml e baixar dependencias (cache layer)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

#Baixar depedencias (essa camada sera cacheada)
RUN ./mvnw dependency:go-offline

#Copiar o codigo fonte
COPY src ./src

#Compilar a aplicacao
RUN ./mvnw clean package -DskipTests

#Stage 2: Runtime (execucao)
FROM eclipse-temurin:21-jre-alpine

#Diretorio de trabalho dentro do container
WORKDIR /app

#Copiar apenas JAR do stage anterior
COPY --from=builder /app/target/ecommerce-0.0.1-SNAPSHOT.jar app.jar

#criar usuario nao-rest para seguranca
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

#Expor a porta da aplicacao
EXPOSE 8080

#Comando para executar a aplicacao
ENTRYPOINT ["java", "-jar", "app.jar"]