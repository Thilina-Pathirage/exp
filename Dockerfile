FROM openjdk:17
ADD target/exp_server-0.0.1-SNAPSHOT.jar exp_server-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "my-app.jar"]
