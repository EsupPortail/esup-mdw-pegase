# Stage that builds the application, a prerequisite for the running stage
FROM maven:3.9.9-eclipse-temurin-21-alpine as build
RUN apk add --update nodejs-current npm

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
COPY --chown=myuser:myuser frontend frontend
COPY --chown=myuser:myuser package.json ./

# Build the production package, assuming that we validated the version before so no need for running tests again
RUN mvn clean package -DskipTests -Pproduction

# Running stage: the part that is used for running the application
FROM tomcat:jdk17
COPY --from=build /usr/src/app/target/*.war /usr/local/tomcat/webapps/ROOT.war
#RUN export JAVA_OPTS="$JAVA_OPTS -Dspring.config.location=/usr/local/application.properties"
EXPOSE 8080
WORKDIR /usr/app/
ENTRYPOINT ["catalina.sh", "run"]
