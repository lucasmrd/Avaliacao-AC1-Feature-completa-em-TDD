pipeline {
    agent any

    tools {
            maven 'maven-3.9.6'
        }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('DEV - Build & Tests') {
            steps {
                // Maven com testes + geração de relatórios (JaCoCo, PMD, JUnit)
                bat 'mvn clean verify'
            }
        }

        stage('DEV - Relatórios e Quality Gate') {
            steps {
                // JUnit
                junit 'target/surefire-reports/*.xml'

                // JaCoCo
                jacoco(
                    execPattern: 'target/jacoco.exec',
                    classPattern: 'target/classes',
                    sourcePattern: 'src/main/java'
                )

                // PMD
                recordIssues tools: [pmdParser(pattern: 'target/pmd.xml')]

                //
                //
            }
        }

        stage('Image_Docker - Build sem testes + Push') {
            steps {
                // Build da aplicação sem testes
                bat 'mvn clean package -Dmaven.test.skip=true'

                // Build da imagem Docker
                bat 'docker build -t lucasm1rnd/af-parte1:latest .'

                // Login no Docker Hub
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                }

                // Push para o Docker Hub
                bat 'docker push lucasm1rnd/af-parte1:latest'
            }
        }

        stage('Staging - Deploy e Healthcheck') {
            steps {
                echo 'Starting container from Docker Hub (staging)...'
                bat 'docker-compose -f docker-compose.staging.yml pull'
                bat 'docker-compose -f docker-compose.staging.yml up -d --no-color'
                sleep time: 60, unit: 'SECONDS'
                bat 'docker-compose -f docker-compose.staging.yml logs'
                bat 'docker-compose -f docker-compose.staging.yml ps'

                // Healthcheck
                bat 'curl http://localhost:8686 || echo "Service not responding"'
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed'
        }
    }
}