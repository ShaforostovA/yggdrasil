FROM maven:3.9.1-amazoncorretto-19-debian

ADD . /yggdrasil
WORKDIR /yggdrasil

RUN mvn package -DskipTests

EXPOSE 80
ENTRYPOINT ["java","-jar","target/yggdrasile.jar"]