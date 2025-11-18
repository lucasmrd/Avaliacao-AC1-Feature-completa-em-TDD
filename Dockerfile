FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace/app

COPY pom.xml ./
COPY src ./src

RUN mvn -B -ntp -DskipTests package

FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY --from=build /workspace/app/${JAR_FILE} app.jar

EXPOSE 8080
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /opt/app/app.jar"]
