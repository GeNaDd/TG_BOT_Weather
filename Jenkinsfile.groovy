pipeline {
    agent any
    environment {
        NAME_PROJECT = 'tgbotweatherGenDevBY'
    }
    stages {
        stage('Build Docker Image') {
            steps {
                echo "===== Docker Build ====="
                sh 'docker build -t gendevbydocker/gendevby_tg_bot_weather .'
            }
        }
        stage('Login DockerHUB') {
            steps {
                echo "===== Login to DockerHUB ====="
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Docker Push Image') {
            steps {
                echo "===== Docker Pushing ====="
                sh 'docker push gendevbydocker/gendevby_tg_bot_weather'
            }
        }
        stage('test2') {
            steps {
                echo "Hello World!"
            }
        }
    }
}
