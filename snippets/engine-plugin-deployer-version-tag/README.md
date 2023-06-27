# Version Tag Change Deployer

For each deployment to the engine, the deployment resources are filtered for BPMN files. In each BPMN file, the version tag is set to a default value.

This is proven by the test case.

## Use this plugin

### Camunda Run

Run `mvn clean package` to receive the `jar`-artifact in the `target` folder of the project. 

Then, install the process engine plugin [as described in the docs](https://docs.camunda.org/manual/latest/user-guide/camunda-bpm-run/#process-engine-plugin-registration).