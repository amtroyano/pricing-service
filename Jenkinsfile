#!usr/bin/env groovy

def SONARQUBE_SERVER_NAME = 'SonarQube'

pipeline {

	agent {
		kubernetes {
			label "springboot"
		}
	}
	
	environment {
		APP_NAME = readMavenPom().getArtifactId()
		APP_ARTIFACT_FILE = readMavenPom().getArtifactId().concat(readMaven.getVersion()).concat('.jar')
	}
	
	options {
		timeout(time: 10, unit: 'MINUTES')
	}
	
	stages {
		stage('Init') {
			steps {
				container('springboot') {
					script {
						switch(env.BRANCH_NAME) {
							case 'master':
								env.ENVIROMENT = 'prd'
								break
							default:
								env.ENVIRONMENT = 'dev'
								break
						}
					}
				}
			}
		}
		
		stage('Build & Tests') {
			steps {
				container('springboot') {
					script {
						sh 'mvn -U clean install  -Djavax.net.ssl.trustStore=src/main/resources/certs/try_cert.jks -Djavax.net.ssl.trustStorePassword=1234'
					}
			}
		}
		
		stage('SonarQube Scanner') {
			steps {
				container('springboot') {
				withSonarQubeEnv(credentialsId: TOKEN, installationName: "${SONAR_SERVER_NAME}") {
					sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
				}
			}
		}
		
		stage('Uploading AWS S3') {
			steps {
				container('springboot') {
					script {
						sh 'mkdir -p target'
						sh 'cp ${APP_NAME}/target/${APP_ARTIFACT_FILE} target/${APP_ARTIFACT_FILE}'
					}
				}
			}
		}
	}
}