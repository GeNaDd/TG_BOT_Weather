pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'docker build -t gendevbydocker/gendevby_tg_bot_weather:test .'
            }
        }
    }
}


