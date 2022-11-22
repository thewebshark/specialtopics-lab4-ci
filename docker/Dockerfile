FROM jenkins/jenkins:lts

# This is the directory jenkins uses for most of its configuration
ENV JENKINS_HOME=/usr/share/jenkins/ref

# Normally when jenkins starts up you need to put in credentials from the logs
# For this use case we want to bypass that
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

# Bootstrap jenkins with the plugins we want to possibly use
COPY --chown=jenkins:jenkins plugins.txt ${JENKINS_HOME}/plugins.txt
#RUN /usr/local/bin/install-plugins.sh < ${JENKINS_HOME}/plugins.txt
RUN jenkins-plugin-cli -f ${JENKINS_HOME}/plugins.txt

# Jenkins will run all of the scripts in init.groovy.d at startup, so we can do helpful
# things like install tools and create and run the seed job
COPY --chown=jenkins:jenkins init.groovy.d/ ${JENKINS_HOME}/init.groovy.d/

# Where jenkins jobs lie
COPY --chown=jenkins:jenkins jobs/ ${JENKINS_HOME}/jobs/

# Where we're putting our dsl job definitions.  In particular, your pipeline job will be here.
COPY --chown=jenkins:jenkins dsl/ ${JENKINS_HOME}/dsl

# Download maven to a location inside the docker image, so we can add a maven Tool location within jenkins (in init.groovy.d)
RUN curl --fail -o ${JENKINS_HOME}/apache-maven-3.6.3-bin.tar.gz https://apache.osuosl.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
