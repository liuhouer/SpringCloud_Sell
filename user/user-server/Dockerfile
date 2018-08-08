FROM java:openjdk-8-jre-alpine

MAINTAINER hellozjf 908686171@qq.com

ADD target/*.jar app.jar
ADD src/main/resources/bootstrap-prod.yml bootstrap.yml

EXPOSE 8200

ENTRYPOINT ["java", "-jar", "/app.jar"]