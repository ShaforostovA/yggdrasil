FROM maven:3.9.1-amazoncorretto-19-debian

RUN apt-get update
RUN apt-get install -y --fix-missing ttf-mscorefonts-installer ttf-ubuntu-font-family
RUN fc-cache -f -v

ADD . /yggdrasil
WORKDIR /yggdrasil

RUN mvn package -DskipTests

EXPOSE 80
ENTRYPOINT ["java","-jar","target/yggdrasile.jar"]