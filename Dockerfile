# Utilisation de l'image OpenJDK 11
FROM openjdk:11-jre-slim
EXPOSE 8089
ADD target/achat-2.0.jar achat-1.0.jar
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]