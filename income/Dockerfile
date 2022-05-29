FROM openjdk:11

ARG JAR_FILE=target/*.jar
RUN mkdir /opt/income
COPY ${JAR_FILE} /opt/income/app.jar
ENTRYPOINT ["java", "-jar", "/opt/income/app.jar"]