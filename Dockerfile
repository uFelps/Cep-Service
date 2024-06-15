FROM openjdk:17-jdk

COPY  /target/cep-service-0.0.1-SNAPSHOT.jar /app/cep-service.jar

CMD ["java", "-jar", "/app/cep-service.jar"]