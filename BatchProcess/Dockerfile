FROM maven:3.9.1-sapmachine-17 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/BatchProcess-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "BatchProcess-0.0.1-SNAPSHOT.jar"]