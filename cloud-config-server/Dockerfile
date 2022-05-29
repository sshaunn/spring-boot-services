FROM openjdk:11

ARG JAR_FILE=target/*.jar
RUN mkdir /opt/cloud-config-server
COPY ${JAR_FILE} /opt/cloud-config-server/app.jar
ENTRYPOINT ["java", "-jar", "/opt/cloud-config-server/app.jar"]