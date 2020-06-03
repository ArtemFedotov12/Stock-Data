ARG MAVEN=maven:3.5.2-jdk-8-alpine
FROM $MAVEN AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn -Dmaven.test.skip=true package


FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/stock.war /app/

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8070

ENTRYPOINT ["java", "-jar", "stock.war"]