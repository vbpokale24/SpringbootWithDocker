FROM java:8-jdk-alpine

COPY ./target/resources-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch resources-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","resources-0.0.1-SNAPSHOT.jar"]