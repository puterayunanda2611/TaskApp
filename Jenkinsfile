pipeline {
    agent {
        node {
            label "linux && java17 && android"
        }
    }

    trigger {
        pollSCM("*/5 * * * *")
    }

    environment {
        AUTHOR = "Putera Yunanda"
        EMAIL = "putera.yunanda@gmail.com"
        CREDENTIAL = credentials("test-credential")
    }

    parameters {
        choice(
            name: "ENVIRONMENT",
            choices: ["Debug", "Release"],
            description: "Select the environment"
        )
    }

    options {
        disableConcurrentBuilds()
        timeout(time: 10, unit: 'MINUTES')
    }

    stages {
        stage("Info") {
            steps {
                echo "Author: ${env.AUTHOR}"
                echo "Email: ${env.EMAIL}"
            }
        }
        stage("Clean") {
            steps {
                echo "Starting the clean process..."
                sh "./gradlew clean"
                echo "Clean completed successfully!"
            }
        }
        stage("Build") {
            steps {
                echo "Starting the build process..."
                sh "./gradlew :app:assemble${params.ENVIRONMENT}"
                echo "Build completed successfully!"
            }
        }
        stage("Test") {
            steps {
                echo "Starting the test process..."
                sh "./gradlew :app:test${params.ENVIRONMENT}UnitTest"
                echo "Tests completed successfully!"
            }
        }
    }

    post {
        success {
            echo "Yay, success!"
        }
        failure {
            echo "On no, failure!"
        }
    }
}
