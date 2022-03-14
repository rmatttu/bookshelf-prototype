FROM openjdk:17-alpine
LABEL maintainer "mggggk <mggggk@gmail.com>"

ENV LC_ALL C.UTF-8
ENV LANG C.UTF-8

WORKDIR /app/

COPY README.md /app/
COPY build.gradle.kts /app/
COPY gradlew /app/
COPY settings.gradle.kts /app/

COPY gradle/ /app/gradle/
COPY src/ /app/src/

RUN ./gradlew build

EXPOSE 8080

ENTRYPOINT ["java"]
CMD [ "-jar", "build/libs/simple-bookshelf-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=default" ]
