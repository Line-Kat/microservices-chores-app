# Use the official OpenJDK image from the Docker Hub
FROM openjdk:20-jdk

# Copy the application jar file to the container
COPY ./target/user-0.0.1-SNAPSHOT.jar /usr/src/user/

# Set the working directory inside the container
WORKDIR /usr/src/user/

# Expose the application port
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "user-0.0.1-SNAPSHOT.jar"]
