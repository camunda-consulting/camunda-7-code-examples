# Process Engine Plugin to handle LDAP Login Codes

This process engine plugin registers a `ExceptionCodeProvider` to hook into the
login mechanism of Active Directory and translate a return code for changing the
user password into a rest response that can used in the
active-directory-frontend-plugin to display a page to change the password.

## Implementation details

The process engine plugin contains two classes.

[ActiveDirectoryExceptionCodeProvider](src/main/java/org/camunda/bpm/plugin/activedirectory/ActiveDirectoryExceptionCodeProvider.java)
implements a provider for exception codes returned by the process engine. This
class intercepts the Exception thrown when a user has to change its password and
checks it against the error codes from LDAP and Active Directory. If the code
matches the configured values, it returns a custom exception code, that has to
be picked up in the frontend plugin. See
[password-policy.js](../active-directory-frontend-plugin/frontend/src/password-policy.js)
how the values are handled.

[ActiveDirectoryErrorCodeProviderPlugin](src/main/java/org/camunda/bpm/plugin/activedirectory/ActiveDirectoryErrorCodeProviderPlugin.java)
registers the `ActiveDirectoryExceptionCodeProvider` as a plugin in the process
engine. It provides default values for the configuration, which can be simply
overridden in the configuration file of the Camunda Run installation. An example
configuration is shown below.

To activate this mechanism, it is required to disable the catch of
`AuthenticationExcpetion`.

## Example config

```yml
camunda.bpm:
  run:
    ldap:
      enabled: true
      serverUrl: ldap://<ip>
      managerDn: <user>
      managerPassword: <password>
      passwordCheckCatchAuthenticationException: false
    process-engine-plugins:
      - plugin-class: org.camunda.bpm.plugin.activedirectory.ActiveDirectoryErrorCodeProviderPlugin
        plugin-parameters:
          ldapErrorCode: 49
          activeDirectoryErrorCode: 773
          camundaCustomExceptionCode: 22222
          exceptionType: javax.naming.AuthenticationException
```

## Build and install

Maven is used to build the process engine plugin: `mvn package`. After a
successful build, the `active-directory-error-code-provider-0.0.1-SNAPSHOT.jar`
has to be copied into the `configuration/userlib` folder of the Camunda Run
installation.
