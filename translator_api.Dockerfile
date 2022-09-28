FROM openjdk:19-alpine
LABEL maintainer="Henrique Guimarães, João Santos"
ADD target/TranslatorApi-0.0.1-SNAPSHOT.jar translator_api.jar
EXPOSE 8080
ENV MYSQL_HOST "mysqldb"
ENTRYPOINT ["java", "-jar", "translator_api.jar"]


