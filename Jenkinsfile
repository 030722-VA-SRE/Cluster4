pipeline {
    agent any
    environment{
        // versionNumber = '1'
        // eksNamespace = 'cluster4'
        // appDeployment = ''
        // appContainer = ''
        registry= 'miggarcia338/chip-shop'
        dockerHubCreds = 'dockerhub'
        dockerImage =''
    }
    stages {
        stage('Code quality analysis'){
            steps{
                withSonarQubeEnv(credentialsId: 'sonar-token', installationName: 'sonar')}{
                    sh 'mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar'
                }
            }

        stage("Maven clean package"){
            steps{
                sh 'mvn clean package -Dmaven.test.skip'
            }
        }

        stage('Build Docker image') {
            steps {
                script {
                        dockerImage = docker.build "$registry"
                    }
            }
        }
        stage("Pushing image to DockerHub") {
            steps {
                script {
                    docker.withRegistry('', dockerHubCreds) {
                        dockerImage.push("$versionNumber.$currentBuild.number")
                        dockerImage.push("latest")
                    }
                }
            }
        }
        stage('Waiting for approval'){
            steps{
                script{
                    try {
                        timeout(time:30, unit: 'MINUTES'){
                            approved = input mesasage: 'Deploy to production?', ok: 'Continue',
                                parameters: [choice(name: 'approved', choices: 'Yes\nNo', description: 'Deploy this build to production')]
                            if(approved != 'Yes'){
                                error('Build not approved')
                            }
                        }
                    } catch (error){
                        error('Build not approved in time')
                    }
                }
            }
        }
        stage("Deploy to production"){
            steps{
                script{
                    withAWS(credentials: 'SRE aws credentials', region: 'us-east-1'){
                        sh 'curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"'
                        sh 'chmod u+x ./kubectl'
                        sh 'aws eks update-kubeconfig --name kevin-sre-1285'
                        sh './kubectl get pods -n chip-shop'
                        sh "echo $registry:$currentBuild.number"
                        sh "./kubectl set image -n chip-shop deployment/demo-deployment demo-container=$registry:$currentBuild.number"
                    }
                }
            }
        }
    }
}