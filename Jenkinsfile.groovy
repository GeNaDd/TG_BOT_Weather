pipeline {
    agent any
    environment {
        NAME_PROJECT = 'tgbotweatherGenDevBY'
        NAME_IMAGE_DEV = 'gendevbydocker/gendevby_tg_bot_weather'
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
