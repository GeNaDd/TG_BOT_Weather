pipeline {
    agent any
    environment {
        NAME_PROJECT = 'tgbotweatherGenDevBY'
        DOCKERHUB_CREDENTIALS = credentials('gendevbydocker')
        NAME_IMAGE_DEV = 'gendevbydocker/gendevby_tg_bot_weather:latest'
    }
    stages {
        stage('Build Docker Image') {
            steps {
                echo "===== Docker Build ====="
                sh 'docker build -t ${NAME_IMAGE_DEV} .'
            }
        }
        stage('Docker Push Image') {
            steps {
                echo "===== Docker Pushing ====="
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker push ${NAME_IMAGE_DEV}'
            }
        }
        stage('test2') {
            steps {
                echo "Hello World!"
            }
        }
    }
}
