FROM openjdk:8-jre

ADD orderService/target/orderService.jar /app/orderService.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "\/app\/orderService.jar"]