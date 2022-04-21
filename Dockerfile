FROM maven:3.6.3-openjdk-8 as builder
COPY ./chip-shop/src/ src/
COPY ./chip-shop/pom.xml pom.xml
RUN mvn clean package -Dmaven.test.skip -Pprod

FROM java:8 as runner
COPY --from=builder target/chip-shop.jar chip-shop.jar
ENTRYPOINT ["java", "-jar", "/chip-shop.jar"]