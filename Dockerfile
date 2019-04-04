FROM maven:3.5-jdk-8 as maven
WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN curl -s https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-3.3.0.1492-linux.zip && \
    unzip sonar-scanner-cli-3.3.0.1492-linux.zip
COPY sonar-project.properties ./sonar-scanner-3.3.0.1492-linux/conf/sonar-project.properties
RUN mvn test -B -V && \
    bash <(curl -s https://codecov.io/bash) -c -F unit && \
    mvn clean verify -Dskip.surefire.tests=true -B -V && \
    bash <(curl -s https://codecov.io/bash) -c -F integration && \
    mvn package && cp target/magnetrade-*.jar app.jar && \
    sonar-scanner -Dsonar.projectBaseDir=./src

FROM openjdk:8u171-jre-alpine
WORKDIR /app
COPY --from=maven /app/app.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]