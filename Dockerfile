FROM maven:3.9.4-jdk-17 AS build

WORKDIR /workspace/app

COPY pom.xml mvnw* ./
COPY .mvn .mvn

RUN mvn -B -ntp dependency:go-offline

COPY src ./src

RUN mvn -B -ntp -DskipTests package

FROM eclipse-temurin:17-jre-jammy

ENV APP_HOME=/opt/app
RUN mkdir -p ${APP_HOME} && groupadd -r app && useradd -r -g app -d ${APP_HOME} -s /sbin/nologin app && chown -R app:app ${APP_HOME}

WORKDIR ${APP_HOME}

ARG JAR_NAME=target/*.jar
COPY --from=build /workspace/app/${JAR_NAME} app.jar

USER app

EXPOSE 8080

ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseContainerSupport"
ENV SPRING_PROFILES_ACTIVE=prod

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /opt/app/app.jar"]
