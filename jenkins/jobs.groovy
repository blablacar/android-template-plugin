final String rootFolder = 'android-jobs/template-plugin'
final String pipelinesFolder = 'jenkins'
final String mainBranch = 'master'

return [[name       : "$rootFolder/template-plugin-publish-release",
         jenkinsfile: "$pipelinesFolder/JenkinsfilePublishRelease",
         branch     : mainBranch]]
