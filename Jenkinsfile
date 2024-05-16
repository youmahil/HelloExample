pipeline{
	// 0.Jenkins에 의해 선택된 가용 Agent를 사용한다 
	agent any
	
	stages{
	   // 1.GIT에서 가져온 소스를 Gradle로 빌드한다. 
       stage('Gradle Build Java Source') {
           steps {
                sh "chmod 700 gradlew"
                sh "./gradlew bootJar --parallel --continue"
            }
        }
	
	   // 2.Docker Image을 빌드하고, Private Harbor Registry에 Push한다.
       stage('Docker Image Build and Push to Harbor Registry') {
            steps {
            	withDockerRegistry([credentialsId: "Yaong-localHarbor-Credentials", url: "https://harbor.yaong.domain/"]) {
	            	sh '''
	            	docker build -t harbor.yaong.domain/testproject/echo-demo:0.0.1 .
	                docker push harbor.yaong.domain/testproject/echo-demo:0.0.1 
	                '''
                }
            }
        }
	}
}