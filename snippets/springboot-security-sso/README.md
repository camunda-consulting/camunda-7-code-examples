## Integrating Spring Security with Camunda

### Overview

This project showcases the integration of [Spring Security](https://projects.spring.io/spring-security/) with Camunda, so one can create a 
SSO solution based on Spring Security and the [Camunda Spring Boot starter](https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration/).

The main idea is to offload authentication to Spring Security which then makes it easy to plug in any authentication 
mechanism. Spring Security offers different ways of achieving that:

*Option 1:* Provide a custom Spring Security `AuthenticationProvider` which takes care of the whole authentication procedure.
In that way, authentication could be done in any (proprietary) Camunda specific way. This approach is not in scope 
of this showcase.

*Option 2:* Use any given Identity Management solution (e.g. keycloak, auth0, ...) and integrate it with Spring Security,
then pass the authentication on to Camunda with a custom Camunda `AuthenticationFilter`. 
Depending on what one needs to secure, one needs to add a [stateless filter](https://github.com/camunda-consulting/code/blob/master/snippets/springboot-security-sso/src/main/java/com/camunda/demo/filter/rest/StatelessUserAuthenticationFilter.java) for the REST API 
(similar to `ProcessEngineAuthenticationFilter`, 
see [here](https://github.com/camunda/camunda-bpm-platform/blob/master/engine-rest/engine-rest/src/main/java/org/camunda/bpm/engine/rest/security/auth/ProcessEngineAuthenticationFilter.java) )
or a [session based one](https://github.com/camunda-consulting/code/blob/master/snippets/springboot-security-sso/src/main/java/com/camunda/demo/filter/webapp/SpringSecurityBasedUserAuthenticationFilter.java) for the web apps (similar to `AuthenticationFilter`, see [here](https://github.com/camunda/camunda-bpm-webapp/blob/master/src/main/java/org/camunda/bpm/webapp/impl/security/auth/AuthenticationFilter.java)).
 
We also need to make sure that the existing AuthenticationFilter that is already used by the Web Apps does not interfere
with our custom one. That means our filter should be first in the order, so that the existing one can serve as a fallback. 
Since the existing filter only reads auth info from the session, there should be no harm in leaving it in place.
 
If one needs to use IdentityService APIs or wants to see actual Users and Groups show up in Cockpit,
a custom `IdentityProvider` needs to be implemented as well.
 
Another example of how to use external authentication can be found in the [Camunda SSO JBoss Project](https://github.com/camunda/camunda-sso-jboss).
 
