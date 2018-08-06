FROM java:openjdk-8-jre-alpine

MAINTAINER hellozjf 908686171@qq.com

ADD target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]