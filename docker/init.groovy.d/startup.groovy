import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.domains.Domain
import javaposse.jobdsl.plugin.GlobalJobDslSecurityConfiguration
import jenkins.model.GlobalConfiguration

/*
  This script runs when jenkins starts up (by virtue of being in init.groovy.d.  This particular
  script creates and runs the seed job (which then creates the other jobs for this jenkins instance
*/

// Disable warning that new version of Jenkins available
hudson.model.AdministrativeMonitor.all()
        .grep{ it instanceof hudson.model.UpdateCenter$CoreUpdateMonitor }
        .each{ it.disable(true) }

// disable Job DSL script approval
GlobalConfiguration.all().get(GlobalJobDslSecurityConfiguration.class).useScriptSecurity=false
GlobalConfiguration.all().get(GlobalJobDslSecurityConfiguration.class).save()

instance = jenkins.model.Jenkins.getInstance()
globalConfig = instance.getDescriptorByType(GlobalJobDslSecurityConfiguration.class)
globalConfig.useScriptSecurity = false
globalConfig.save()

// Set up the seed job(s) from the file that has been placed inside the docker.

jenkins_home = System.getenv()['JENKINS_HOME']

logger = java.util.logging.Logger.getLogger('seed.groovy')
jenkins = jenkins.model.Jenkins.getInstance()

def createAndBuildSeedJob(seedJobDsl) {
    logger.info(seedJobDsl)

    jobManagement = new javaposse.jobdsl.plugin.JenkinsJobManagement(System.out, [:], new File("${jenkins_home}/workspace"))

    items = new javaposse.jobdsl.dsl.DslScriptLoader(jobManagement).runScript(seedJobDsl)
    items.jobs.each { dslJob ->
        logger.info("Created job: ${dslJob.jobName}")
        job = jenkins.getJob(dslJob.jobName)
        job.scheduleBuild(0)
    }
}
createAndBuildSeedJob(new File("${jenkins_home}/jobs/seed.groovy").newReader().text)
