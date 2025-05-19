
FROM maven:3.9-eclipse-temurin-17

WORKDIR /app

# Copy the Maven POM file
COPY pom.xml .

# Copy the source code
COPY src ./src

# Package and run the application
EXPOSE 8080
CMD ["mvn", "quarkus:dev"]