
node {
  stage('checkout sources') {
        // You should change this to be the appropriate thing
        git url: 'https://github.com/thewebshark/specialtopics-lab4-ci'
  }

  stage('Build') {
    // you should build this repo with a maven build step here
    echo "hello"
    withMaven (maven: 'maven3') {
      sh "mvn package"
    }
  }
  // you should add a test report here

}
