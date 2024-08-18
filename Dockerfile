# Use the ARM-compatible Selenium Standalone Chromium image
FROM seleniarm/standalone-chromium:114.0
# Set the working directory inside the container
WORKDIR /app
# Copy Gradle wrapper scripts and project files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle /app/
COPY src /app/src
# Make the Gradle wrapper executable
USER root
RUN chmod +x gradlew
# Run Gradle build to compile the project
#RUN ./gradlew build
# Set the default command to run your tests
# Comando por defecto para ejecutar las pruebas
CMD ["./gradlew", "build"]