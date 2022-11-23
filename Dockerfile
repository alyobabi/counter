FROM openjdk:14-jdk-alpine
COPY target/counter-0.0.1-SNAPSHOT.jar counter.jar
ENTRYPOINT ["java","-jar","/counter.jar"]

