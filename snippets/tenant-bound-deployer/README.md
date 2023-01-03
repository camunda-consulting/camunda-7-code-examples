# Tenant Bound Deployer

The default behaviour of the process engine is to allow deployments to tenants that the user creating the deployment is not assigned to.

This process engine plugin provides a modification of this behaviour in a way that the deployment is verified to have a tenant which the user is assigned to.

In any other case, the deployment will be rejected.

This is proved by the test case.

## Use this plugin

### Camunda Run

Run `mvn clean package` to receive the `jar`-artifact in the `target` folder of the project. 

Then, install the process engine plugin [as described in the docs](https://docs.camunda.org/manual/latest/user-guide/camunda-bpm-run/#process-engine-plugin-registration).