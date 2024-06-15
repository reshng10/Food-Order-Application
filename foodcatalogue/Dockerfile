#FROM openjdk:17-jdk-alpine
#WORKDIR /opt
#COPY target/*.jar /opt/app.jar
#ENTRYPOINT exec $JAVA_OPTS -jar app.jar

FROM openjdk:17-jdk-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]