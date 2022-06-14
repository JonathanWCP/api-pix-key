FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} pix-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/pix-0.0.1-SNAPSHOT.jar"]