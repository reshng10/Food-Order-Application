FROM openjdk:17-jdk-alpine
WORKDIR /opt
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec $JAVA_OPTS -jar app.jar