FROM maven:3.8.2-openjdk-16 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:16-alpine3.13
WORKDIR /
ARG JAR_FILE=SecurityEnggAssignment-1.0.jar
COPY --from=build /home/app/target/${JAR_FILE} /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
