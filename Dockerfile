# Utilisation de l'image OpenJDK 11
FROM openjdk:19
WORKDIR /app
RUN curl -o app.jar -L "http://192.168.1.77:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "app.jar"]