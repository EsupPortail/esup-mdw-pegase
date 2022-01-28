# syntax=docker/dockerfile:experimental
FROM maven:3-jdk-11 AS builder
WORKDIR /build
COPY pom.xml /build/
COPY src /build/src
COPY frontend /build/frontend
RUN --mount=type=cache,target=/root/.m2/repository mvn -Pmaven-frontend -Pproduction --batch-mode --errors --show-version package

FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY --from=builder /build/target/webapp/WEB-INF/lib /app/lib
COPY --from=builder /build/target/webapp/META-INF /app/META-INF
COPY --from=builder /build/target/webapp/WEB-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","fr.univlorraine.mondossierweb.Application"]
