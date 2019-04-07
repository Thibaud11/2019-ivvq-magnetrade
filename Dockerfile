FROM maven:3.6-jdk-8 as maven
WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
#RUN mvn test -B -V && \
#    bash <(curl -s https://codecov.io/bash) -c -F unit && \
#    mvn clean verify -Dskip.surefire.tests=true -B -V && \
#    bash <\(curl -s https://codecov.io/bash\) -c -F integration && \
#    mvn package && cp target/magnetrade-*.jar app.jar
RUN mvn package && cp target/magnetrade-*.jar app.jar

FROM openjdk:8u171-jre-alpine
WORKDIR /app
COPY --from=maven /app/app.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]