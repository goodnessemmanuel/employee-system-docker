FROM openjdk:11-jdk-slim

COPY pom.xml /build/

WORKDIR /build/

COPY src /build/src/

EXPOSE 8080

ADD target/employee-management-system.jar /build/employee-management-system.jar

ENTRYPOINT ["java", "-jar", "/build/employee-management-system.jar"]