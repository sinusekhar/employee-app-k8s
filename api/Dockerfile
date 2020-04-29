FROM maven:3.6.3-jdk-8 as builder
WORKDIR '/app'
COPY pom.xml pom.xml
COPY src src
RUN ["mvn","package"]

FROM openjdk:8
COPY --from=builder /app/target/employee-app.jar /app/app.jar

CMD ["java", "-jar","-Denv_type=aws","-Dcatalina.home=/","/app/app.jar"]
