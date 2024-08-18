# Selenium-Cucumber-Gradle Project

This project is a test automation framework built using Selenium, Cucumber, and Gradle. It supports both UI and API testing, with a clear structure to manage tests, configurations, and reporting.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Prerequisites](#prerequisites)
3. [Setup Instructions](#setup-instructions)
4. [Continuous Integration](#continuous-integration)
5. [Docker Setup](#docker-setup)
6. [Customization](#customization)
7. [Contributing](#contributing)
8. [License](#license)

## Project Structure

- **Dockerfile**: Defines the Docker image used to run the tests in a containerized environment.
- **build.gradle**: The main configuration file for Gradle, specifying dependencies, tasks, and plugins.
- **Jenkinsfile**: Pipeline script used by Jenkins for continuous integration.
- **docker-compose.yml**: Configuration file for Docker Compose to manage multi-container Docker applications.
- **src/test/java**: Contains the following key components:
  - **stepdefinitions/**: This directory includes Java classes that define the steps used in your Cucumber feature files. Each method in these classes corresponds to a step in a Cucumber scenario and contains the code that interacts with the application under test.
  - **runners/**: This directory contains the Cucumber runner classes. These classes are annotated with `@RunWith(Cucumber.class)` and specify the configuration for running the Cucumber tests, such as the location of feature files and the format of the test reports.
  - **hooks/**: This folder contains hook classes, which are annotated with `@Before`, `@After`, `@BeforeStep`, and `@AfterStep`. Hooks allow you to run code before and after each scenario or step, which is useful for setup and teardown tasks, such as launching or closing a browser.
  - **utils/**: Utility classes that provide helper methods for commonly used functionalities in your tests, such as handling web elements, managing test data, or interacting with APIs.
- **gradlew** / **gradlew.bat**: Gradle wrapper scripts for Unix and Windows systems respectively.
- **target/cucumber-reports.html**: HTML report generated after running the tests.

## Prerequisites

- Java 11 or higher
- Gradle 7.0 or higher
- Docker and Docker Compose (optional, for running in containers)
- Jenkins (optional, for CI/CD pipeline)

## Setup Instructions

1. **Clone the repository**:

   git clone https://github.com/your-repo/selenium-cucumber-gradle-project.git
   cd selenium-cucumber-gradle-project

2. **Build the project**:

   ./gradlew clean build

3. **Run the tests**:

   - To run tests locally:

     ./gradlew test

   - To run tests inside Docker:

     docker-compose up --build

4. **View the test reports**:

   After the tests have run, you can view the HTML report located at:

   target/cucumber-reports.html

## Continuous Integration

This project includes a `Jenkinsfile` for CI/CD. The pipeline includes steps for:

- Building the project
- Running the tests
- Publishing the test results

To set up Jenkins:

1. Install Jenkins and the necessary plugins (`Gradle Plugin`, `Docker Plugin`).
2. Create a new pipeline job and point it to the repository.
3. Jenkins will automatically pick up the `Jenkinsfile` and execute the defined stages.

## Docker Setup

The project includes a Dockerfile and a docker-compose.yml for containerized execution.

- **Dockerfile**: Defines the environment for running the tests.
- **docker-compose.yml**: Orchestrates the execution of the tests in Docker.

To build and run the Docker container:

docker-compose up --build

This will build the Docker image, spin up the container, and execute the tests inside the container.

## Customization

- **Configuration**: Test configurations (e.g., browser settings, URLs) can be modified in the `src/test/resources` directory.
- **Step Definitions**: Located in `src/test/java/stepdefinitions`.
- **Feature Files**: Located in `src/test/resources/features`.

## License

TO-DO
