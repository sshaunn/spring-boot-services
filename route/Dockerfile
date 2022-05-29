FROM openjdk:11

ARG JAR_FILE=target/*.jar
RUN mkdir /opt/route
COPY ${JAR_FILE} /opt/route/app.jar
ENTRYPOINT ["java", "-jar", "/opt/route/app.jar"]