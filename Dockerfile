FROM node:17-alpine AS sass_docker

ADD . /app
RUN npm install -g sass
RUN sass --no-source-map app/src/main/resources/sass:/app/src/main/resources/public/css

FROM heroku/heroku:20 AS heroku_project

RUN apt-get update \
  && apt-get install -y maven \
  && apt-get clean \
  && rm -Rf /var/lib/apt/lists/*
RUN mkdir /app

WORKDIR /app

ADD pom.xml /app
# RUN mvn dependency:resolve

ADD . /app
COPY --from=sass_docker /app/src/main/resources/public/css /app/src/main/resources/public/css

ARG PRODUCTION
ENV PRODUCTION=$PRODUCTION
RUN scripts/docker/mvn_if_production.sh

CMD mvn spring-boot:run

