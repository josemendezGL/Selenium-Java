pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "selenium-cucumber-gradle-project:latest"
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image using the Dockerfile in the project root
                    docker.build(DOCKER_IMAGE)
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run the tests inside the created Docker container
                    docker.image(DOCKER_IMAGE).inside {
                        sh 'gradle clean test --tests "com.example.testrunners.APITestRunner" -Denv=qa'
                    }
                }
            }
        }
    }

    post {
        always {
            // Archive the test reports
            archiveArtifacts artifacts: '**/build/reports/**', allowEmptyArchive: true

            // Publish the test results in Jenkins
            junit '**/build/test-results/test/TEST-*.xml'
        }
        failure {
            // Send a notification in case of failure
            mail to: 'jose.mendez@gorillalogic.com',
                 subject: "Build failed: ${currentBuild.fullDisplayName}",
                 body: "The build has failed: ${env.BUILD_URL}"
        }
    }
}
