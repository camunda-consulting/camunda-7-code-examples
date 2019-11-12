# Bypass Expensive LDAP Queries

This project includes an AuthenticationFilter and an AuthenticationProvider to bypass LDAP queries for a special user.

Background: If you run Camunda in a high load scenario with external task and enabled security, the engine checks the authentication for each call and reads the user and his groups from the LDAP server. This caused timeouts in LDAP requests in a customer environment.

To overcome this, you may use a special user and configure his credentials outside of LDAP in a special file.

The AuthenticationFilter in the `engine-rest.war` now should check the user id from the request against this special user first and if its not found here, ask LDAP for the user.

### Tomcat Setup
In the Tomcat shared engine it's quite easy to set up the bypass.

1. Build the jar file with `mvn clean install`.
2. Copy the resulting file `target\authentication-filter-with-bypass-1.1.0.jar` into the WEB-INF/lib-folder of the extracted engine-rest war under `camunda-bpm-tomcat-7.10.0\server\apache-tomcat-9.0.12\webapps\engine-rest\WEB-INF\lib\`
3. Wire the classes from this project in the `web.xml` of engine rest (`camunda-bpm-tomcat-7.10.0\server\apache-tomcat-9.0.12\webapps\engine-rest\WEB-INF\web.xml`):
```
...
  <filter>
    <filter-name>camunda-auth</filter-name>
    <filter-class>
      com.camunda.consulting.rest.auth.AuthenticationFilterWithBypass
    </filter-class>
    <init-param>
      <param-name>authentication-provider</param-name>
      <param-value>com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass</param-value>
    </init-param>
    <async-supported>true</async-supported>
  </filter>
...
```
4. Create a file named `bypassUser.properties` in the configuration folder of the tomcat server: `camunda-bpm-ee-tomcat-7.10.0\server\apache-tomcat-9.0.12\conf\`. Here is an example: [bypassUser.properties](src/test/resources/bypassUser.properties).
5. The configuration must contain the keys `userId`, `passwd` and `groups`. `groups` contains a comma separated list of existing groups. The groups are required to check authorizations.
6. Start the server and check the configuration in the logs. You should find a snippet like:
```
INFO [localhost-startStop-1] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.<init> Initializing AuthenticationProviderWithBypass
INFO [localhost-startStop-1] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.readBypassValues bypass userId set successfully
INFO [localhost-startStop-1] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.readBypassValues bypass password set successfully
INFO [localhost-startStop-1] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.readBypassValues bypass groups set successfully
```
7. Check the configuration by call the rest api with a rest client of your choice. You should be asked for authorization and your configured bypass user should pass without any call to the LDAP server. To confirm that it's working correctly, increase the logging level of the filter. It's described below. You should find the following output in the console:
```
05-Sep-2019 22:03:06.354 FEIN [http-nio-8080-exec-7] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.isAuthenticated Check authentication for someUserId
05-Sep-2019 22:03:06.355 FEIN [http-nio-8080-exec-7] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.isAuthenticated Authenticated as bypassUser
05-Sep-2019 22:03:06.358 FEIN [http-nio-8080-exec-7] com.camunda.consulting.rest.auth.AuthenticationFilterWithBypass.setAuthenticatedUser get groups and tenants for authenticated user someUserId
05-Sep-2019 22:03:06.360 FEIN [http-nio-8080-exec-7] com.camunda.consulting.rest.auth.AuthenticationFilterWithBypass.setAuthenticatedUser set user and group from bypassUser
```

### Wildfly setup
1. Change the lines 37-42 of the AuthenticationProviderwithBypass to
```
String bypassConfigFileLocation = System.getProperty("jboss.server.config.dir") + File.separator + BYPASS_USER_CONFIG_FILE_NAME;
```

2. Same as Tomcat above.
3. Same as Tomcat above.
4. Create a file named `bypassUser.properties` in the configuration folder of the wildfly server: `camunda-bpm-ee-wildfly-7.10.0\server\wildfly-14.0.1-Final\configuration\`. Here is an example: [bypassUser.properties](src/test/resources/bypassUser.properties).
5. to 7. Same as Tomcat above.

### How it works
The configuration file read on startup and the values a held in main memory until the server is restarted. To change the password, you have to restart the tomcat server.

#### Logging
The filter adds additional debug output on the Log level `com.camunda.consulting.rest.auth.AuthenticationFilterWithBypass`.

To enable the output add this line to the `logging.properties` file in the `conf` directory:

```
com.camunda.consulting.rest.auth.AuthenticationFilterWithBypass.level = FINE
```

### Tested environment
Camunda 7.10.0 Tomcat distribution.