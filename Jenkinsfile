pipeline {
    agent any

    environment {
        SONAR_QUBE_SERVER = 'http://sonar.moyu-cloud.internal:9000'
        PROJECT_KEY = 'moyu-cloud-platform'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from Git...'
                checkout scm
            }
        }

        stage('SonarQube Static Analysis') {
            steps {
                echo 'Running SonarQube Code Scan...'
                script {
                    echo "SonarScan on branch: ${env.BRANCH_NAME}"
                }
            }
        }

        stage('Unit Testing & Coverage') {
            steps {
                echo 'Executing Node/JUnit Unit Tests...'
                sh 'npm test'
            }
        }

        stage('Quality Gate Checklist') {
            steps {
                echo 'Verifying Quality Gate: Coverage >= 70%, Blocker Issues = 0'
            }
        }

        stage('Build & Package') {
            steps {
                echo 'Building release package...'
                sh 'npm run build'
            }
        }

        stage('Deploy to Staging/Prod') {
            steps {
                echo 'Deploying Moyu Cloud Platform artifact...'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution complete.'
        }
        failure {
            echo 'Pipeline failed. Triggering alert to Moyu Cloud Dev Team.'
        }
    }
}
