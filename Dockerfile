
FROM amazoncorretto:11-alpine
EXPOSE 8080
WORKDIR /app
COPY target/library-0.0.1-SNAPSHOT.war .
ENTRYPOINT [ "java", "-jar", "library-0.0.1-SNAPSHOT.war"]