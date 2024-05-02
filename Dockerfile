FROM openjdk:22
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} backendAutofix.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./backendAutofix.jar"]