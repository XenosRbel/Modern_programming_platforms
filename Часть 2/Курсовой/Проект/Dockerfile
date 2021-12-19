FROM maven:3.8.4-amazoncorretto-17
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar


CMD ["java","-jar","app.jar"]