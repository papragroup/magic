From openjdk:11
copy ./target/magic-body-0.0.1-SNAPSHOT.jar magic.jar
CMD ["java","-jar","magic.jar"]
