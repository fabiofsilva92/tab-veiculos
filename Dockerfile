
FROM openjdk:17-jdk-slim as build

WORKDIR /app

COPY . .

RUN ./mvnw clean package

FROM openjdk:17-jdk-slim

COPY --from=build /app/target/*.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/application.jar"]