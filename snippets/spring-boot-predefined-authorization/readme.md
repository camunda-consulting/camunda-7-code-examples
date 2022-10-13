# Predefined authorization plugin

This plugin enables the configuration of authorizations. The concrete implementation is done in spring boot and uses the `application.yaml` file.

## How it works

### Core functionality

You can define a set of [`AuthorizationRules`](./src/main/java/com/camunda/consulting/AuthorizationRules.java). Each [`AuthorizationRule`](./src/main/java/com/camunda/consulting/AuthorizationRule.java) consists of some attributes:
- type: authorization type (0,1 or 2)
- resource: the type of resource
- user: the name of the user, only 1 of user or group can be applied
- group: the name of the group, only 1 of user or group can be applied
- permissions: the permissions granted with this authorization
- resourceId: optional, the id of the resource this authorization is meant for

These [`AuthorizationRules`](./src/main/java/com/camunda/consulting/AuthorizationRules.java) can be set to the [AuthorizationProcessEnginePlugin](./src/main/java/com/camunda/consulting/AuthorizationProcessEnginePlugin.java) which will insert them after the process engine was built.

### Spring Boot implementation

To define [`AuthorizationRules`](./src/main/java/com/camunda/consulting/AuthorizationRules.java) via `application.yaml` file, some mapping is required.

Basic property mapping happens in [`AuthorizationProperties`](./src/main/java/com/camunda/consulting/AuthorizationProperties.java). 

Then, these properties are translated to [`AuthorizationRules`](./src/main/java/com/camunda/consulting/AuthorizationRules.java) in [`AuthorizationConfig`](./src/main/java/com/camunda/consulting/AuthorizationConfig.java).

They are then used to provide a bean of type [AuthorizationProcessEnginePlugin](./src/main/java/com/camunda/consulting/AuthorizationProcessEnginePlugin.java) containing all rules defined.