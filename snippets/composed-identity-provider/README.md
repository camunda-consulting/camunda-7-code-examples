# Composed Identity Provider Example

This example shows how a composed identity provider can be implemented.

It uses:

* one LDAP configurations
* a static in-memory bypass

## Goal

Using this composed identity provider, you will be able to an LDAP connection plus a bypass.

## Configuration

### Spring Boot

To use the plugin with Spring Boot, the easiest way would be to add it to the context as `@Bean` programmatically. From there, it will be registered to the process engine.

> Please set `additionalUserProperties` to prevent loading the properties file from catalina conf.
> 
> Instead, you can inject here a subsection of the application.yaml

### Camunda Run

Here, the same mechanism as with Spring Boot would apply as the plugin needs to be configured.

### Tomcat

To use the plugin with a Tomcat, you will need to register it to the `bpm-platform.xml`. Just replace the `plugin.class` of the LDAP identity provider plugin with `com.camunda.consulting.ComposedLdapIdentityProviderPlugin`:

As this plugin extends the Ldap Identity Provider plugin, you should be able to use it with the same properties.

The plugin will then the ldap configuration plus the in-memory identity provider.

If you need to populate the in-memory identity provider as well, please add to the plugin properties:

```xml
<property name="additionalUsers">{{additionalUsersPropertiesFileName}}</property>
```

This will default to `additionalUsers.properties`. The file has to be placed under `conf/` in the `catalina.home`.

Additional users, groups and assignments can then be registered in the properties file like this:

```properties
# Creates a user with a given userId
user.{{userId}}.firstName=Demo
user.{{userId}}.lastName=Demo
user.{{userId}}.password=demo
user.{{userId}}.email=demo@demo.org

# Creates groups with given ids
group.{{groupId}}.name=Demo Group
group.{{groupId}}.type=SYSTEM

group.{{groupId2}}.name=Demo Group 2
group.{{groupId2}}.type=SYSTEM

# Assigns a user to the given groups, separated by comma
assignment.{{userId}}=demoGroup, demoGroup2
```

## Components

### Plugin

The process engine plugin that registers the session factory to the engine.

Here, all configuration from outside is taken and processed before the factory receives session factories.

### Session Factory

The session factory that provides the identity provider itself. It extends the LDAP identity provider session factory.

### Identity Provider

The session for one request. It overrides the query methods to execute the LDAP query first and the in-memory query afterwards.
