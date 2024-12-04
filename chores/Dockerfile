# Use the official OpenJDK image from the Docker Hub
FROM openjdk:20-jdk

# Copy the application jar file to the container
COPY ./target/chores-0.0.1-SNAPSHOT.jar /usr/src/chores/

# Set the working directory inside the container
WORKDIR /usr/src/chores/

# Expose the application port (adjust to your app's port) via denne porten kan det gjøres kall inn til kontaineren
EXPOSE 8082

# Run the application
CMD ["java", "-jar", "chores-0.0.1-SNAPSHOT.jar"]
