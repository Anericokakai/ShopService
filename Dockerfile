FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM  openjdk:17.0.1-jdk-slim
COPY --from=build /target/shopsService-0.0.1-SNAPSHOT.jar shopService.jar
EXPOSE 0
ENTRYPOINT ["java","-jar","shopService.jar"]