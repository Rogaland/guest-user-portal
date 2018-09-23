FROM openjdk:8-jre-alpine

ADD guest-user-portal-backend/build/libs/guest-user-portal-backend-*.jar /data/app.jar

CMD ["java", "-jar", "/data/app.jar"]