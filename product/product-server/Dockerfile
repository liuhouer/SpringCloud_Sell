FROM java:openjdk-8-jre-alpine

MAINTAINER hellozjf 908686171@qq.com

ADD target/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/app.jar"]