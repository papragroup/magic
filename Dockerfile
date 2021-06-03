From openjdk:8
copy ./target/magic-body-0.0.1-SNAPSHOT.jar magic.jar
CMD ["java","-jar","etrat.jar"]
