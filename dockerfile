# Stage 1: Java
FROM amazoncorretto:17-alpine-jdk as java-app

COPY docspotback/target/docspotback-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java","-jar","/app.jar" ]

EXPOSE 8000

# Stage 2: Node.js
FROM node:18-alpine

WORKDIR /usr/src/app

COPY . /docspotfront/src/app
COPY ./docspotfront/package.json ./


RUN npm install -g @angular/cli

RUN npm install
WORKDIR /usr/src/app/docspotfront # <--- Add this line

CMD ["ng", "serve", "--host", "0.0.0.0"]
EXPOSE 4200