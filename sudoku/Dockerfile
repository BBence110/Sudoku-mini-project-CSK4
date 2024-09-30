FROM maven:3.9-eclipse-temurin-21 AS build

# Copy the Java Application source code
COPY . /usr/src/

# Build Java Application
RUN mvn -f /usr/src/pom.xml clean install -DskipTests

FROM openjdk:21
WORKDIR /app

# Copy the JAR file from the build stage (/app)
COPY --from=build /usr/src/target/*.jar ./java.jar

# Expose the port the app runs on
EXPOSE 9090

# Run the jar file
CMD ["java", "-jar", "java.jar"]