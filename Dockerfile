#
#   Build-Stage
#
FROM maven:3.8.4-openjdk-11-slim AS build
MAINTAINER useless-ktor-mfarkan.com
WORKDIR /tmp
COPY . .
RUN mvn verify -U --no-transfer-progress -Dmaven.test.skip=true -Dmaven.repo.local=/.m2/repository


#
#   Run-Stage
#d
FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=build /tmp/target/*-jar-with-dependencies.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","/useless-ktor-0.0.1-jar-with-dependencies.jar"]