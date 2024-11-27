# Use the official OpenJDK 20 image as a parent image
FROM openjdk:20-jdk

# Set the working directory in the container
WORKDIR /usr/src/gateway

# Copy the JAR file into the container
COPY ./target/Gateway-0.0.1-SNAPSHOT.jar /usr/src/gateway

# Expose the port that the gateway service will run on
EXPOSE 8000

# Define the command to run the gateway service
ENTRYPOINT ["java", "-jar", "Gateway-0.0.1-SNAPSHOT.jar"]
