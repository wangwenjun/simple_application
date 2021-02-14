pipeline{
    agent {label 'devops'}
    tools{
        maven 'maven-3.5.2'
        jdk   'jdk1.8'
    }
    stages{
        stage("Initialize"){
            steps{
                sh '''
                    echo "PATH=${PATH}"
                    echo "MAVEN_HOME=${MAVEN_HOME}"
                '''
            }
        }
        stage("Clone source code"){
            steps{
                git branch: 'develop', url: "git@github.com:wangwenjun/simple_application.git"
            }
        }

        stage("Deploy to Nexus Repo"){
            steps{
                sh 'mvn -Ddependency-check.skip clean deploy '
            }
            post{
                success{
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage("Deployment to Apps Host"){
            steps{
                ansiblePlaybook  playbook: 'my_playbook.yml', extraVars: 'version=0.0.1-SNAPSHOT'
                ansiColor('xterm') {
                    ansiblePlaybook(
                        playbook: '/home/wangwenjun/training/ansible/simple_application_deploy.yml',
                        extraVars: 'version=0.0.1-SNAPSHOT',
                        colorized: true)
                }
            }
        }

        stage("Function Acceptance Testing"){
            steps{
                echo 'Trigger Function Acceptance Testing'
            }
        }
    }
}