pipeline {
    agent any
    environment {
        NAME_PROJECT = 'tgbotweatherGenDevBY'
        DOCKERHUB_CREDENTIALS = test'test'
        NAME_IMAGE_DEV = 'gendevbydocker/gendevby_tg_bot_weather:latest'
    }
    stages {
        stage('build dockerimage') {
            steps {
                sh 'docker build -t gendevby_tg_bot_weather:Test .'
            }
        }
    }
    stage('push devimage') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker push ${NAME_IMAGE_DEV}'
                sh 'docker rmi ${NAME_IMAGE_DEV}'
                //Удаляем рабочие директории проекта
                cleanWs()
                    dir("${env.WORKSPACE}@tmp") {
                        deleteDir()
                    }
                }
        }
}


