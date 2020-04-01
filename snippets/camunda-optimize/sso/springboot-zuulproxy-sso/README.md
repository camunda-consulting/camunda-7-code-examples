## Enabling Single Sign-On for Optimize with ZuulProxy

### Overview
This project is an example on how to integrate a Zuul Proxy with Optimize to enable SSO. [Optimize SSO Plugin](https://docs.camunda.org/optimize/latest/technical-guide/plugins/single-sign-on/) implements the interface `AuthenticationExtractor`, which only reads basic `HttpServletRequest`.
These functions are enriched by using a ZuulProxy that handles authorization, enrichs HTTP requests with corresponding header identifier and forwards rqeuests to the OptimizeSsoPlugin.

This has been tested with Optimize 2.7.0 and Camunda BPMN 7.12.0.
Optimize AuthenticationFiltering is still active under Optimize login path.

### ZuulProxy
This example uses a `PreFilter` of the [Zuul Request Lifecycle](https://github.com/Netflix/zuul/wiki/How-it-Works) that forwards requests on `localhost:8111/optimize/` to Optmize.
Configurations can be found under ZuulProxy/src/main/resources/application.yml

### Optimize SSO Plugin
The OptimizeSsoPlugin reads header information of `HttpServletRequest` and forwards the `AuthenticationResult` to Optimize.
## Installation
Build fat jar(OptimizeSsoPlugin-jar-with-dependencies) with Maven:
```
mvn clean package
```
Put jar under:
```
%OptimizeInstallationPath%/plugin/OptimizeSsoPlugin-jar-with-dependencies.jar
```
Edit:
```
%OptimizeInstallationPath%/environment/environment-config.yaml
```
and add: 
```
plugin:
  authenticationExtractor:
    basePackages: ["org.camunda.com.ssoplugintest"]
```
Start Camunda BPMN, Optimize and ZuulProxy.

Other examples of how to use authentication can be found in [Integrating Spring Security with Camunda](https://github.com/camunda-consulting/code/tree/master/snippets/springboot-security-sso), [Camunda SSO JBoss Project](https://github.com/camunda/camunda-sso-jboss), and in [Keycloak Spring Boot SSO snippet](https://github.com/camunda-consulting/code/tree/master/snippets/springboot-keycloak-sso)

