pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerjubjenkins')
    }
    stages {
        stage('Build'){
            steps{
                sh 'docker build -t gendevbydocker/gendevby_tg_bot_weather:latest .'
            }
        }
    }
    stage('Login') {
        steps {
            sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
        }
    }
    stage('Push'){
        steps {
            sh 'docker push gendevbydocker/gendevby_tg_bot_weather:latest'
        }
    }
}
post {
    always {
        sh 'docker logout'
    }
}
