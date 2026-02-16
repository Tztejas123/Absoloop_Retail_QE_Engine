pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                git url: 'https://github.com/Tztejas123/Absoloop_Retail_QE_Engine.git'

            }
        }

        stage('Execute Tests') {
            steps {
                bat 'runRegression.bat'
            }
        }

    }
}
