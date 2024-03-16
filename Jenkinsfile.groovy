pipeline {
    agent any
    environment {
    }
    stages {
        stage('build dockerimage') {
            steps {
                sh 'docker build -t gendevby_tg_bot_weather:Test .'
            }
        }
    }

