FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/src/main/resources/HDBCarparkInformation.csv ./resources/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
