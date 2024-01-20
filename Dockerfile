FROM openjdk:17

WORKDIR /App

COPY /target/rentacar-0.0.1-SNAPSHOT.jar .

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "rentacar-0.0.1-SNAPSHOT.jar"]