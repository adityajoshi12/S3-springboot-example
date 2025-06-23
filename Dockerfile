FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app


COPY pom.xml /app/

# Copy the source code
COPY src /app/src

RUN apt-get update && apt-get install -y maven

# Install dependencies and build the application
RUN mvn clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/spring-s3-demo-0.0.1-SNAPSHOT.jar"]
