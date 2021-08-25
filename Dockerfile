#FROM openjdk:11-jre-slim-buster
FROM adoptopenjdk/openjdk11:latest
#MAINTAINER sv
#ADD /build/distributions/myretail.tar /
#ENTRYPOINT ["/myretail/bin/myretail"]
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
