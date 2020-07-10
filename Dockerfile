FROM openjdk:12-alpine

EXPOSE 3001

ADD /build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]