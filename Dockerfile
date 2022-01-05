FROM openjdk:8
ADD build/libs/external-0.0.1-SNAPSHOT.jar external-0.0.1.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar", "external-0.0.1.jar"]