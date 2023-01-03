final String rootFolder = 'android-jobs/template-plugin'
final String pipelinesFolder = 'jenkins'
final String mainBranch = 'master'

final String debugParameters = '''
    parameters {
        stringParam('branch', '', 'branch to publish, e.g. feature/BBC-XXXX or bugfix/BBC-XXXX')
      }
'''

return [[name       : "$rootFolder/template-plugin-publish-debug",
         jenkinsfile: "$pipelinesFolder/JenkinsfilePublishDebug",
         branch     : mainBranch,
         extra      : debugParameters],

        [name       : "$rootFolder/template-plugin-publish-release",
         jenkinsfile: "$pipelinesFolder/JenkinsfilePublishRelease",
         branch     : mainBranch]]
