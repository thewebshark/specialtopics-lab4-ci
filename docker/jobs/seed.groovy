import javaposse.jobdsl.dsl.helpers.LocalRepositoryLocation
import javaposse.jobdsl.dsl.helpers.publisher.PublisherContext

/* This is the seed job definition.  It's role is to create all of the
   jobs for this jenkins instance (other than itself, of course).

   Those jobs are in the dsl directory
*/

job('seed') {
    description('this job creates other jobs')

    steps {
        shell {
            command('cp ${JENKINS_HOME}/dsl/*.groovy .')
        }
        dsl {
            external('**/*.groovy')
            ignoreExisting(false)
        }
    }
}
