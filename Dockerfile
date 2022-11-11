FROM openjdk:17-ea-jdk-slim
VOLUME /tmp
COPY /build/libs/oction-service-0.0.1-SNAPSHOT.jar oction-service-0.0.1.jar
ENTRYPOINT ["java","-jar","oction-service-0.0.1.jar"]