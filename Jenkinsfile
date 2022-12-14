#!/usr/bin/env groovy

/*

A sample, generic Jenkinsfile with some recommended options and tips.

// TODO(you): Please modify it to fit your needs!

>> Tip: Try to keep logic in external scripts as much as possible for a maintainable pipeline! <<

- Core pipeline syntax docs can be found here: https://www.jenkins.io/doc/book/pipeline/syntax
- Feel free to use custom library steps: https://github.com/blablacar/jenkins-library/tree/master/vars
- BlaBlaCar Jenkins documentation is here: https://blablacar.atlassian.net/wiki/spaces/ARCH/pages/219061888/Jenkins

*/

// Often useful to set these
// Note: Should match the actual repo configuration; consider using 'main' to be future-proof!
env.GIT_DEFAULT_BRANCH = 'master'
final Boolean isDefaultBranch = env.BRANCH_IS_PRIMARY == 'true' // equivalent to `env.BRANCH_NAME == env.GIT_DEFAULT_BRANCH`

// Free resources by allowing newer runs of the same build to supersede running or queued ones
if (!isDefaultBranch) stopOlderBuilds()

pipeline {
    agent { label 'standard' }

    options {
        // TODO(you): Uncomment if you want to override the global default
        // "Keep history of the 30 most recent builds that are at most 30 days old"
        // buildDiscarder(logRotator(numToKeepStr: '30', daysToKeepStr: '30'))
        // Set a reasonable build timeout to ensure builds don't hang indefinitely
        timeout(time: 30, unit: 'MINUTES')
        // Any failed parallel stage will abort the whole block
        parallelsAlwaysFailFast()
        // Enable pretty console output
        ansiColor('xterm')
    }

    parameters {
        // Can be useful to force-push the container image (to later deploy to preprod via Flux automation)
        booleanParam(
            name: 'PUSH',
            defaultValue: isDefaultBranch,
            description: 'Whether or not to push the built container image to the registry',
        )

        // Can be useful to force-deploy an arbitrary branch artifact to the production environment
        booleanParam(
            name: 'DEPLOY',
            defaultValue: isDefaultBranch,
            description: 'Whether or not to deploy to production',
        )
    }

    stages {
        // Initialize: Fetch & install project dependencies, compile, etc.
        stage('Init') {
            steps {
                // TODO(you): Add the appropriate command
                sh label: 'Install dependencies & assets', script: '#make install'
            }
        }

        // Check: Source validation & other static analysis
        stage('Check') {
            // Parallelize independent stages for a faster pipeline
            parallel {
                stage('Check: Style') {
                    steps {
                        // TODO(you): Add the appropriate command
                        sh label: 'Lint & static analysis checks', script: '#make check'
                    }
                }

                stage('Check: Lint chart') {
                    // Conditionally run this stage only when something changed in the `_infra/my-app/` directory
                    when { expression { gitFilesChanged('_infra/my-app') } }
                    steps {
                        dir('_infra/my-app') {
                            sh label: 'Lint the Helm chart', script: 'bbc charts lint'
                        }
                    }
                }
            }

            post {
                always {
                    // Attach/publish check results to the build
                    // TODO(you): Adjust tools, pattern, etc. for your use case
                    // See https://github.com/jenkinsci/warnings-ng-plugin/blob/master/doc/Documentation.md
                    recordIssues(
                        tools: [
                            java(),
                            checkStyle(pattern: '**/build/reports/checkstyle/*.xml'),
                        ],
                        enabledForFailure: true,
                        aggregatingResults: true,
                    )
                }
            }
        }

        // Test: Run unit, integration, end-to-end tests, etc.
        stage('Test') {
            parallel {
                stage('Test: Fast') {
                    // You can logically group related stages
                    stages {
                        stage('Test: Fast tests') {
                            steps {
                                sh(
                                    label: 'Run "fast" (unit & integration) tests',
                                    // TODO(you): Add the appropriate command
                                    script: '#make test-unit',
                                )
                            }
                        }

                        // Quality gate generally depends on test coverage results
                        stage('Quality gate') {
                            steps {
                                echo "Hello from the '${STAGE_NAME}' stage!"

                                // TODO(you): Add the relevant project files, adjust the `scanCommand` if needed, and uncomment:
                                // sonarScan(
                                //     // Common example (parameter can be omitted for non-Gradle projects with a sonar.properties file):
                                //     scanCommand: 'docker compose -f docker-compose.jenkins.yml run -T code ./gradlew sonarqube',
                                // )
                            }
                        }
                    }
                }

                stage('Test: Slow tests') {
                    steps {
                        // TODO(you): Add the appropriate command
                        sh label: 'Run "slow" (end-to-end) tests', script: '#make test-e2e'
                    }
                }

                // TODO(you): Remove this stage if not a Gradle or Go project
                // Security scan: Monitor dependencies for vulnerabilities
                // See https://blablacar.atlassian.net/wiki/spaces/SEC/pages/329941081/Dependencies+scanning+with+Snyk#Docker-compose-(Gradle)
                stage('Security scan') {
                    when { expression { isDefaultBranch } } // Only monitor the default branch!
                    environment { SNYK_TOKEN = credentials('snyk-api-token') }
                    steps {
                        sh(
                            label: 'Scan & submit dependencies to Snyk to monitor for vulnerabilities',
                            // TODO(you): Uncomment & modify the command appropriately:
                            script: '''
                                #docker compose --file docker-compose.snyk.yml \\
                                #run -T snyk \\
                                #snyk monitor --org=MY-ORG --project-name=MY-PROJECT-NAME
                            ''',
                        )
                    }
                }
            }

            post {
                always {
                    // Attach/publish any test results to the build
                    // Common example:
                    junit allowEmptyResults: true, testResults: '**/build/test-results/*/*.xml'
                }
            }
        }

        // Build: Generate the image/release/artifact/thing that will be delivered
        stage('Build') {
            steps {
                sh(
                    label: 'Build the asset to be deployed',
                    // TODO(you): Add the appropriate command
                    script: '#make build',
                    // Common example:
                    // script: 'REGISTRY_TARGET=bbc-registry bbc docker build test',
                )
            }
        }

        // Deliver: Deploy/release/deliver the artifact
        stage('Deliver') {
            when { expression { params.PUSH || params.DEPLOY } }

            // Common example:
            stages {
                stage('Deliver: Push') {
                    steps {
                        sh(
                            label: 'Push the deployable image to the bbc-registry',
                            // TODO(you): Uncomment script or replace with the appropriate command
                            script: '#bbc docker push',
                        )
                    }
                }
                stage('Deliver: Deploy') {
                    when { expression { params.DEPLOY } }
                    steps {
                        echo "Hello from the '${STAGE_NAME}' stage!"

                        // TODO(you): Add or modify the appropriate command(s), e.g.:
                        // deploy(
                        //     env: 'infra-prod',
                        //     namespace: 'my-namespace',      // e.g. 'spa'
                        //     helmRelease: 'my-helm-release', // e.g. 'kairos'
                        //     slackChannel: '#my-deployments',
                        //     // Require manual confirmation in Jenkins if not ready for Continuous Delivery:
                        //     askConfirmation: true,
                        // )
                    }
                }
            }
        }
    }

    // Steps to perform after the build (e.g. notify & teardown/cleanup)
    post {
        always {
            // Notifies about non-passing builds or build status changes (e.g. when a failed build passes again)
            slackNotifications channels: '#my-team-ci-notifications'
        }
        cleanup {
            // Clean the workspace (may not be desirable in some cases)
            cleanWs()
        }
    }
}
