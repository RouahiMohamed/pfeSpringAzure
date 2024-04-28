FROM openjdk:17
COPY target/AzurePfe-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
ENTRYPOINT ["java","-jar","AzurePfe-0.0.1-SNAPSHOT.jar"]