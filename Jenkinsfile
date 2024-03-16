pipeline {
    agent any
    environment {
        NAME_PROJECT = 'tgbotweatherGenDevBY'
        DOCKERHUB_CREDENTIALS = credentials('gendevbydocker')
        NAME_IMAGE_DEV = 'gendevbydocker/gendevby_tg_bot_weather:latest'
        NAME_CONTAINER_DEV = 'tgbotweatherGenDevBY_dev'
        TAG_IMAGE_PROD = 'prod'
    }
    stages { 
            agent any 
            steps {
                sh 'docker build -t ${NAME_IMAGE_DEV} .'    
            }
    }
}

