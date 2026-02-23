FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:23-jre-alpine
WORKDIR /app
COPY --from=build /app/target/kubernetesTesting-1.0-SNAPSHOT.jar app.jar

EXPOSE 9400

ENTRYPOINT ["java", "-jar", "app.jar"]
