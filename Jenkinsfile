pipeline{
    agent any
    tools{
        maven "maven"

    }
    stages{
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/CamilaCarabajal/backendAutofix']])
                dir("backendAutofix"){
                    bat "mvn clean install"
                }
            }
        }
        stage("Test"){
            steps{
                dir("backendAutofix"){
                    bat "mvn test"
                }
            }
        }
        stage("Build and Push Docker Image"){
            steps{
                dir("backendAutofix"){
                    script{
                         withDockerRegistry(credentialsId: 'docker-credential'){
                            bat "docker build -t CamilaCarabajal/backendAutofix ."
                            bat "docker push CamilaCarabajal/backendAutofix"
                        }
                    }
                }
            }
        }
    }
}