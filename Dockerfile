FROM openjdk:17

WORKDIR /App

COPY /target/rentacar-0.0.1-SNAPSHOT.jar .

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "RentaCar-0.0.1-SNAPSHOT.jar"]