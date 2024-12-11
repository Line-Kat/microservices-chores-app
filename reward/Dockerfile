# Use the official OpenJDK image from the Docker Hub
FROM openjdk:20-jdk

# Copy the application jar file to the container
COPY ./target/reward-0.0.1-SNAPSHOT.jar /usr/src/reward/

# Set the working directory inside the container
WORKDIR /usr/src/reward/

# Expose the application port
EXPOSE 8084

# Run the application
CMD ["java", "-jar", "reward-0.0.1-SNAPSHOT.jar"]
