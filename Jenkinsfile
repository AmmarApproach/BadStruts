#!groovy

pipeline {
    agent none
    options { skipDefaultCheckout() }

    stages {
        stage('Git checkout') {
            agent any
            steps {
                checkout scm
            }
        }

        stage('Build (with Test)') {
            environment { TZ = 'Europe/Brussels' }
            agent any
            steps {
                sh './gradlew build --stacktrace'
                step([$class: 'Publisher'])
            }
        }

        stage('Build war') {
            environment { TZ = 'Europe/Brussels' }
            agent any
            steps {
                sh './gradlew war --stacktrace'
                step([$class: 'Publisher'])
            }
        }

        stage('Dependency Check') {
            agent any
            steps {
                script {
                    dependencycheck additionalArguments: ' — project BadStruts — scan /owasp/ — out dependency-check-report.xml — format XML — noupdate', odcInstallation: 'Dependency Checker'
                    dependencyCheckPublisher pattern: 'dependency-check-report.xml', failedTotalCritical: 1
                    archiveArtifacts allowEmptyArchive: true, artifacts: '**/dependency-check-report.xml', onlyIfSuccessful: true
                    sh 'mv dependency-check-report.xml dependency-check-report-owasp.xml'
                }
            }
        }
    }
}
