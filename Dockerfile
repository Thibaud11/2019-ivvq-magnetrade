FROM maven:3.6-jdk-8 as maven
WORKDIR /app
ENV CODECOV_TOKEN "04330578-d6b9-41d0-8b94-ef3664319218"
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src

# Copie du .git pour codecov
COPY ./.git ./.git

RUN curl -s https://codecov.io/bash > test.sh && \
    mvn test -B -V && \
    bash test.sh -c -F unit && \
    mvn clean verify -Dskip.surefire.tests=true -B -V && \
    bash test.sh -c -F integration && \
    mvn package && cp target/magnetrade-*.jar app.jar

FROM openjdk:8u171-jre-alpine
WORKDIR /app
COPY --from=maven /app/app.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]