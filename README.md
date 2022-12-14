<!-- TODO(you): Replace with your project's name. -->
# template

<!-- TODO(you): Replace with a high-level description of your project here; one or two sentences max, e.g.: -->
Minimal base template repository for BlaBlaCar's private &amp; internal repositories.
<!-- Plugin description -->
The aim of this plugin is to make easier the creation of the differents layer of the clean architecture Data - Domain - Presentation Layer
<!-- Plugin description end -->

<!-- TODO(you): Modify the rest of this file to describe how to use or contribute to the project. -->
## Install

Install dependencies and assets:

```console
$ make install
```


## Run

Use these commands to compile code & assets and start the service.

### For development

To compile code & assets in development/debug mode:

```console
$ make dev
```

### For production

This will compile assets in production mode:

```console
$ make prod
```


## Test

### Static analysis checks

```console
$ make check
```

### Unit & integration tests

```console
$ make test # Same as `make test-unit && make test-integ`
```

### End-to-end tests

```console
$ make test-e2e
```

### All tests

```console
$ make test-all
```


## Deliver

Build and deploy/release the service or application.
<!-- TODO(you): Strive for true continuous delivery! Then eventually uncomment this next line: -->
<!-- Note that a production delivery is automatically triggered on every merge to the default branch. -->
### Build the deliverable

```console
$ make build
```

### Deploy to pre-production

<!-- TODO(you): Update this with an accutate description of your preproduction deployment workflow. -->
From Jenkins, [launch a build](https://jenkins.tools-1.blbl.cr/job/BlaBlaCar/job/template/view/change-requests/job/master/build?delay=0sec) (choosing the desired branch) with the `PUSH` parameter selected to push the container image to the registry, after which you can deploy it via Flux.

### Deploy to production

Every merge to the default branch triggers a deployment build on Jenkins (which might require an additional manual confirmation).

You can also [manually launch a build](https://jenkins.tools-1.blbl.cr/job/BlaBlaCar/job/template/view/change-requests/job/master/build?delay=0sec) (choosing any desired branch) with the `DEPLOY` parameter selected to deploy that branch to production.

### Observe

During and after a deployment, you will want to monitor the service.

<!--
TODO(you): If you have useful project-specific commands, feel free to include them, or link to external documentation for generic observability patterns if you prefer.

Here are some commands you can use:

```console
$ fluxctl ... # make observe-flux
$ kubectl ... # make observe-kube
$ helm ... # make observe-helm
```
-->

Here are some dashboards to observe:
* [My Dashboard 1](https://app.datadoghq.eu/dashboard/lists)
* [My Dashboard 2](https://app.datadoghq.eu/dashboard/lists)

Additionally, please monitor the following relevant Slack channels:
* [#my-deployments](https://blablacar.slack.com/app_redirect?channel=my-deployments)
* [#my-alerts](https://blablacar.slack.com/app_redirect?channel=my-alerts)


## Troubleshooting

### I try to do X, but Y happens!

This is probably because Z. You should do A, B, and C.

<!--
TODO(you): If relevant, feel free to link to a runbook for your service or application, e.g.:

### Something weird is happening after I deploy!

Please check out the [My App Runbook](...) for more detailed troubleshooting tips.
-->
