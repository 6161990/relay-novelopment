FROM openjdk:17.0.1

ARG FROM_JAR=application/build/libs/application-1.0-SNAPSHOT.jar

COPY ${FROM_JAR} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-XX:MaxRAM=256M", "-Xmx128M", "-Xss256k", "-Xms128M", "-jar","app.jar"]