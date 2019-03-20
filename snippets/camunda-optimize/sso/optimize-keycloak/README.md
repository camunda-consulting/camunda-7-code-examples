# Camunda Optimize Keycloak SSO Example

This example demonstrates how you can enable SSO with Optimize and Keycloak.

The most important part from the Optimize perspective of this example is the custom Optimize SSO Plugin in:
[./optimize/sso-keycloak-example-plugin/](./optimize/sso-keycloak-example-plugin/)
To find out more about the Optimize SSO Plugin mechanism, find the docs [here](https://docs.camunda.org/optimize/latest/technical-guide/plugins/single-sign-on/)

It includes a docker-compose with:
1. Camunda Optimize
2. ElasticSearch
3. Camunda BPM
2. Keycloak Authentication Server
3. Keycloak Proxy Server

The Keycloak Server has one user:

```
demo:notdemo
```

# How to run?

## Clone the Repo

## Download and Configure Optimize

1. Download [Camunda Optimize](https://camunda.org/enterprise-release/optimize/2.3.0/camunda-optimize-2.3.0-standalone.zip) to folder `./optimize/`
2. Rename to `camunda-optimize.zip`
3. Place Optimize license in OptimizeLicense.txt
4. Run `mvn package` in `./optimize/sso-keycloak-example-plugin`

## Run docker-compose

1. Build all images with `docker-compose build`
1. Login to private Camunda Docker EE Registry with `docker login registry.camunda.cloud` Use your EE LDAP credentials to log in.
1. Start all images `docker-compose up -d`

## Open Optimize

1. Open WebBrowser
2. Open `localhost:8095`
3. Login with: `demo:notdemo`

# Some more background

Keycloak is responsible for Authentication, so the users are stored in Keycloak and the Keycloak Proxy makes sure that only authenticated users can see Optimize.

In the Optimize Plugin we only read the authenticated user from the request header. If the user is in the request, we authenticate the user directly in Optimize.

# Show me some some code

```java
package com.camunda.optimize.plugin.sso;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.camunda.optimize.plugin.security.authentication.AuthenticationExtractor;
import org.camunda.optimize.plugin.security.authentication.AuthenticationResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeycloakAuthenticationProvider implements AuthenticationExtractor {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request) {
		AuthenticationResult result = new AuthenticationResult();
		String user = request.getHeader("KEYCLOAK_USERNAME");

		if (user == null || user.isEmpty()) {
			logger.info("Did not find user.");
			result.setAuthenticated(false);
			return result;
		} else {
			logger.info("User logged info", user);
			result.setAuthenticatedUser(user);
			result.setAuthenticated(true);
			return result;
		}
	}
}
```
