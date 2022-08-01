# Custom Deployment Authorization

## Idea

The engine can be fed with every kind of resources at runtime by using deployments. The built-in mechanism does not allow denial of resources by key. This plugin enables management of deployment authorization by resource type and key.

## How to

The project holds a test class. The test engine is configured to use the plugin.

During the tests, the 2 methods will deploy a resource that the user has the right to deploy or has not the right to deploy.

As proof, the amount of deployments available is measured.