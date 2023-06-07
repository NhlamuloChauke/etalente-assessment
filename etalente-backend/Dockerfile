FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} filepdf.jar

ENTRYPOINT ["java", "-jar", "/filepdf.jar"]