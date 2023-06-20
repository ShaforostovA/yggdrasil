FROM maven:3.9.1-amazoncorretto-19-debian

ADD . /yggdrasil
WORKDIR /yggdrasil

RUN chmod +x ./mvnw
RUN mvn package -DskipTests

EXPOSE 8080
ENTRYPOINT ["java","-jar","target/yggdrasile.jar"]