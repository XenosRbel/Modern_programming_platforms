FROM maven:3.8.2-openjdk-8

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

CMD ["java","-jar","/app.jar"]
