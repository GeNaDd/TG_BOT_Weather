pipeline {
    agent any
    environment {
        NAME_PROJECT = 'tgbotweatherGenDevBY'
        
        NAME_IMAGE_DEV = 'gendevbydocker/gendevby_tg_bot_weather:latest'
    }
    stages {
        stage('build dockerimage') {
            steps {
                sh 'docker build -t gendevby_tg_bot_weather:Test .'
            }
        }
    }
}


