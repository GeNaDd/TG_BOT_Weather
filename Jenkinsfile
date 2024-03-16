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

        stage('build devimage') { 
            agent any 
            
            steps {
                sh 'docker build -t ${NAME_IMAGE_DEV} .'    
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
        stage('test devimage & push prodimage') {
            agent any
             steps {
                script {
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                    sh 'docker pull ${NAME_IMAGE_DEV}'
                    sh 'docker run -d --name ${NAME_CONTAINER_DEV} -d --rm -p 8082:8082 ${NAME_IMAGE_DEV}'
                    sh 'ping -c 15 localhost'

                    sh 'curl http://localhost:8082'

                    docker.image("${NAME_IMAGE_DEV}").tag("${TAG_IMAGE_PROD}")
                    docker.image("${NAME_IMAGE_DEV}").push("${TAG_IMAGE_PROD}")

                    sh 'docker stop -t 5 ${NAME_CONTAINER_DEV}'
                    sh 'docker system prune -af'
                }
            } 
        }
    }
}

