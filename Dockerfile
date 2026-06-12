# Stage that builds the application, a prerequisite for the running stage
FROM maven:3.9-eclipse-temurin-21-alpine as build
#RUN apk add --update nodejs-current npm
RUN apk add --update nodejs npm && npm install -g pnpm@9.15.0

# Stop running as root at this point
RUN adduser -D myuser
WORKDIR /usr/src/app/
RUN chown myuser:myuser /usr/src/app/
USER myuser

# Copy pom.xml and prefetch dependencies so a repeated build can continue from the next step with existing dependencies
COPY --chown=myuser pom.xml ./
RUN mvn dependency:go-offline -Pproduction

# Copy all needed project files to a folder
COPY --chown=myuser:myuser src src
COPY --chown=myuser:myuser package.json ./

# Build the production package, assuming that we validated the version before so no need for running tests again
RUN mvn clean package -DskipTests -Pproduction

# Running stage: the part that is used for running the application
FROM eclipse-temurin:21-jre-alpine
RUN adduser -D myuser
USER myuser
WORKDIR /usr/app/
COPY --from=build /usr/src/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
