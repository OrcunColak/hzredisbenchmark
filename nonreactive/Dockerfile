# docker build -t nonreactive-app .
# docker image ls -a
# Stage 1: Build the application code and install the dependencies
FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
ENV HOME=/app
WORKDIR $HOME

ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD .. $HOME
RUN mvn package

# Stage 2: Copy the application artifacts to the final image
FROM openjdk:17-slim

WORKDIR $HOME

COPY --from=build /app/nonreactive/target/*.jar .

EXPOSE 8080

CMD ["java", "-jar", "nonreactive-1.0.0.jar"]