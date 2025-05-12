# Usando uma imagem base do OpenJDK
FROM openjdk:17-jdk

COPY target/brecho-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]

# Expor a porta que o Spring Boot usa (geralmente 8080)
EXPOSE 8080