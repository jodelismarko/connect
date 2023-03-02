FROM openjdk:11.0.9.1

COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]