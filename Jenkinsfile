
node {
  stage('checkout sources') {
        git url: 'https://github.com/thewebshark/specialtopics-lab4-ci'
  }

  stage('Build') {
    echo "hello"
    withMaven (maven: 'maven3') {
      sh "mvn package"
    }
  }
  // you should add a test report here

}
