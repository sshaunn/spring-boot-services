FROM openjdk:11

ARG JAR_FILE=target/*.jar
RUN mkdir /opt/service-registration
COPY ${JAR_FILE} /opt/service-registration/app.jar
ENTRYPOINT ["java", "-jar", "/opt/service-registration/app.jar"]