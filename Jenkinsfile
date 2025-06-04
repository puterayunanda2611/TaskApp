pipeline {
    agent {
        node {
            label "linux && java21 && android"
        }
    }

    triggers {
        pollSCM("* * * * *")
    }

    options {
        disableConcurrentBuilds()
        timeout(time: 10, unit: 'MINUTES')
    }

    environment {
        AUTHOR = "Putera Yunanda"
        EMAIL = "putera.yunanda@gmail.com"
    }

    parameters {
        choice(
            name: "ENVIRONMENT",
            choices: ["Debug", "Release"],
            description: "Select the environment"
        )
        booleanParam(
            name: "DISTRIBUTE",
            defaultValue: false,
            description: "Need to distribute to app tester?"
        )
        string(
            name: "RELEASE_NOTES",
            defaultValue: "",
            description: "Please fill release note if want to distribute to app tester"
        )
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
        stage("Deploy") {
            input {
                message "Deploy to App Tester?"
                ok "Yes, deploy!"
                parameters {
                    string(name: 'VERSION', defaultValue: '1.0.0', description: 'Version to deploy')
                }
            }
            steps {
                echo "Deploying version ${params.VERSION} to App Tester..."
                echo "Deployment completed successfully!"
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
