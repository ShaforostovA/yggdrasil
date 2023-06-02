FROM openjdk:19-jdk-alpine
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/yggdrasile.jar"]