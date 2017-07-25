# Bypass Expensive LDAP Queries

This project includes an AuthenticationFilter and an AuthenticationProvider to bypass LDAP queries for a special user.

Background: If you run Camunda BPM in a high load scenario with external task and enabled security, the engine checks the authentication for each call and reads the user and his groups from the LDAP server. This caused timeouts in LDAP requests in a customer environment.

To overcome this, you may use a special user and configure his credentials outside of LDAP in a special file.

The AuthenticationFilter in the `engine-rest.war` now should check the user id from the request against this special user first and if its not found here, ask LDAP for the user.

### Setup
In the Tomcat shared engine it's quite easy to set up the bypass.

1. Build the jar file with `mvn clean install`.
2. Copy the resulting file `target\authentication-filter-with-bypass-1.0.0.jar` into the WEB-INF/lib-folder of the extracted engine-rest war under `camunda-bpm-ee-tomcat-7.7.1-ee\server\apache-tomcat-8.0.24\webapps\engine-rest\WEB-INF\lib\`
3. Wire the classes from this project in the web.xml of engine rest: 
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
...
```
4. Create a file named `bypassUser.properties` in the configuration folder of the tomcat server: `camunda-bpm-ee-tomcat-7.7.1-ee\server\apache-tomcat-8.0.24\conf\`
5. The configuartion must contain the keys `userId`, `passwd` and `groups`. `groups` contains a comma separated list of existing groups. The groups are required to check authorizations.
6. Start the server and check the configuration in the logs. You should find a snippet like:
```
INFO [localhost-startStop-1] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.<init> Initializing AuthenticationProviderWithBypass
INFO [localhost-startStop-1] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.readBypassValues bypass userId set successfully
INFO [localhost-startStop-1] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.readBypassValues bypass password set successfully
INFO [localhost-startStop-1] com.camunda.consulting.rest.auth.AuthenticationProviderWithBypass.readBypassValues bypass groups set successfully
```
7. Check the configuration by call the rest api with a rest client of your choice. You should be asked for authorization and your configured bypass user should pass without any call to the LDAP server.

### How it works
The configuration file read on startup and the values a held in main memory until the server is restarted. To change the password, you have to restart the tomcat server.

### Tested environment
Camunda 7.7 Tomcat distribution.