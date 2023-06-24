FROM maven:3.9.1-amazoncorretto-19-debian

RUN echo "deb http://deb.debian.org/debian buster contrib non-free" > /etc/apt/sources.list.d/contrib-non-free.list
RUN apt-get update
RUN apt-get -y install ttf-mscorefonts-installer ttf-ubuntu-font-family fontconfig
RUN fc-cache -f -v

ADD . /yggdrasil
WORKDIR /yggdrasil

RUN mvn package -DskipTests

EXPOSE 80
ENTRYPOINT ["java","-jar","target/yggdrasile.jar"]