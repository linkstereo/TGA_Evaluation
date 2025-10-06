# Use OpenJDK 21 base image
FROM openjdk:21

# Copy the built JAR into the container (replace path as needed)
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
# If using Maven:
# COPY target/your-app.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]