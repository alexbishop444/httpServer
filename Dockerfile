FROM gradle:jdk11 as base
WORKDIR /webApp
COPY --chown=gradle:gradle . /webApp
RUN ["gradle", "build", "--no-daemon", "-x", "test"]

FROM openjdk:14-alpine
WORKDIR /webApp
COPY --from=base /webApp/build/libs/*.jar /webApp/build.jar
EXPOSE 8005
CMD ["java", "-jar", "build.jar"]