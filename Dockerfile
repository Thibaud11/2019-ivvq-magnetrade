# Build front
FROM node:10.15.3-alpine as nodejs
WORKDIR /app
COPY ./src/main/front ./front
RUN npm install -g @angular/cli && npm install
RUN cd front \
    && npm run build \
    && mv dist /app/public

# Build Back
FROM maven:3.6-jdk-8 as maven
WORKDIR /app
ENV CODECOV_TOKEN "04330578-d6b9-41d0-8b94-ef3664319218"
COPY ./pom.xml ./pom.xml
COPY ./src ./src
COPY ./.git ./.git
COPY --from=nodejs /app/public ./src/main/resources/public
RUN mvn dependency:go-offline -B
RUN curl -s https://codecov.io/bash > test.sh \
    && mvn test -B -V \
    && bash test.sh -c -F unit \
    && mvn clean verify -Dskip.surefire.tests=true -B -V \
    && bash test.sh -c -F integration \
    && mvn package \
    && cp target/magnetrade-*.jar app.jar

# Prepare for launch
FROM openjdk:8u171-jre-alpine
WORKDIR /app
COPY --from=maven /app/app.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]