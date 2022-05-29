FROM openjdk:11

ARG JAR_FILE=target/*.jar
RUN mkdir /opt/employee
COPY ${JAR_FILE} /opt/employee/app.jar
ENTRYPOINT ["java", "-jar", "/opt/employee/app.jar"]