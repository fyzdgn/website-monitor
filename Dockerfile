# Use official OpenJDK 17 base image
FROM openjdk:17

# Set working directory inside the container
WORKDIR /app

# Copy Java source code into the image
COPY src/ /app/src/

# Compile all Java files into /app/out
RUN javac -d out $(find src -name "*.java")


# Set default command to run your main class
 CMD ["java", "-cp", "out", "com.websitemonitor.WebsiteMonitorApp"]


