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
            
      // 3. Kustomize
      stage('Pull git repository') {
            steps {
                git branch: 'main', url: 'https://github.com/yellowsunn/argocd-manifest'
                }
            }
        
      stage('Install kustomize') {
            steps {
                sh """
                    if [ ! -f "${env.WORKSPACE}/kustomize" ]; then
                        curl -s "https://raw.githubusercontent.com/kubernetes-sigs/kustomize/master/hack/install_kustomize.sh" | bash
                    fi
                    ${env.WORKSPACE}/kustomize version
                    """
                }
            }
        
      stage('Change imag tag') {
            steps {
                sh """
                    cd $baseDirectory
                    ${env.WORKSPACE}/kustomize edit set image $imageName:$tag
                    """
                }
            }
        
      stage('Push to manifest repo') {
            steps {
                withCredentials([usernamePassword(credentialsId: '073c37ff-f218-4988-8490-8fbe46760674', passwordVariable: 'gitPassword', usernameVariable: 'gitUsername'), string(credentialsId: 'github_email', variable: 'gitEmail')]) {
                    sh """
                        git config user.email $gitEmail
                        git config user.name $gitUsername
                        git add -A
                        git commit -m '[jenkins] update image tag = $imageName:$tag'
                        git push https://${gitUsername}:${gitPassword}@github.com/yellowsunn/argocd-manifest.git
                        """
                    }
                }
            }            
            
        }
	}
}