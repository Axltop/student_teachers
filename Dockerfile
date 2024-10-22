# Use the official Gradle image as the build environment
FROM gradle:8.8-jdk17 AS build
# Set the working directory
WORKDIR /app
# Copy the Gradle wrapper and configuration files
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .
# Copy the application source code
COPY src/ ./src/
# Build the application
RUN ./gradlew build --no-daemon
# Use a smaller base image for the runtime
FROM openjdk:17-jdk-slim
# Set the working directory
WORKDIR /app
# Copy the built JAR from the previous stage
COPY --from=build /app/build/libs/*.jar app.jar
# Command to run the application
CMD ["java","-jar","app.jar"]