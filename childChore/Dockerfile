# Use the official OpenJDK image from the Docker Hub
FROM openjdk:20-jdk

# Copy the application jar file to the container
COPY ./target/childChore-0.0.1-SNAPSHOT.jar /usr/src/childchore/

# Set the working directory inside the container
WORKDIR /usr/src/childchore/

# Expose the application port (adjust to your app's port) via denne porten kan det gjøres kall inn til kontaineren
EXPOSE 8083

# Run the application
CMD ["java", "-jar", "childChore-0.0.1-SNAPSHOT.jar"]
