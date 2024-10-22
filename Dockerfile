# Usar uma imagem base oficial do Maven e JDK 17
FROM maven:3.8.6 AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar os arquivos do projeto para dentro do container
COPY . .

# Rodar o Maven para construir o projeto
RUN mvn clean package -DskipTests

# Usar uma imagem base para rodar o aplicativo
FROM openjdk:17

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado pela etapa de build para dentro do container
COPY --from=build /app/target/biblioteca-elo-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta em que o Spring Boot vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]