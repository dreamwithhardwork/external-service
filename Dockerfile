FROM openjdk:11
ADD build/libs/external-0.0.1-SNAPSHOT.jar external-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "external-0.0.1.jar"]