FROM heroku/heroku:20

RUN apt-get update && apt-get install -y maven
RUN mkdir /app

WORKDIR /app

ADD pom.xml /app
# RUN mvn dependency:resolve

ADD . /app

RUN mvn install -Dmaven.test.skip=true

CMD mvn spring-boot:run
