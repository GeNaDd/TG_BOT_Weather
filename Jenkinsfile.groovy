pipeline {
    agent any
    environment {
        NAME_PROJECT = 'tgbotweatherGenDevBY'
        DOCKERHUB_CREDENTIALS = credentials('dockerhubjenkins')
        NAME_IMAGE_DEV = 'gendevbydocker/gendevby_tg_bot_weather:latest '
        NAME_CONTAINER_DEV = 'test_gendevby_tg_bot_weather_dev'
        TAG_IMAGE_PROD = 'TEST'
    }
    stages {
        stage('Build Docker Image') {
            steps {
                echo "===== Docker Build ====="
                sh 'docker build -t ${NAME_IMAGE_DEV} .'
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
                sh 'docker push ${NAME_IMAGE_DEV}'
                sh 'docker rmi ${NAME_IMAGE_DEV}'
                //Удаляем рабочие директории проекта
                cleanWs()
                    dir("${env.WORKSPACE}@tmp") {
                        deleteDir()
                    }
            }
        }
        stage ('Test DockerIMAGE AND DockerPUSH') {
            steps{
                script {
                     sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                     sh 'docker pull ${NAME_IMAGE_DEV}'
                     docker.image("${NAME_IMAGE_DEV}").tag("${TAG_IMAGE_PROD}")
                     docker.image("${NAME_IMAGE_DEV}").push("${TAG_IMAGE_PROD}")
 
                     sh 'docker stop -t 5 ${NAME_CONTAINER_DEV}'
                     sh 'docker system prune -af'
                }
            }
        }
    }
}
