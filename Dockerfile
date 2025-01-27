FROM openjdk:22
ARG JAR_FILE=target/*.jar
COPY ./target/rh-0.0.1-SNAPSHOT.jar app.jar
WORKDIR /tmp
ENTRYPOINT ["java","-jar","/app.jar"]