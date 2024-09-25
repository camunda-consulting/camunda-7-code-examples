# SSO Plugin for Camunda Run with Keycloak

Tested with versions:

* `7.21.2-ee` of Camunda Run
* `7.21.6` of Camunda Keycloak Plugin

## What is the purpose?

Currently, there is an Identity Provider Plugin for Keycloak present in the Community Hub. This snippet is based on this community project and adds a fat jar to Camunda Run that allows for configuring SSO using Spring Security.

## How do I get this up and running?

### Install Camunda Run

First, we will need a Camunda Run distribution. The latest one can be found [here](https://downloads.camunda.cloud/enterprise-release/camunda-bpm/run/).

### Install Identity Provider Plugin

Then, we will need to extend this distribution with the Camunda Run extension from the above-mentioned community project. Therefore, head over to the [community artifactory](https://artifacts.camunda.com/ui/native/camunda-bpm-community-extensions/org/camunda/bpm/extension/camunda-platform-7-keycloak-run) and download the matching extension version as jar file.

Place the jar file in the camunda-bpm-run distro under `configuration/userlib`.

### Install SSO Plugin

Make sure that in the `pom.xml`, the properties `version.camunda-bpm` and `version.spring-boot` match the used Camunda Run distribution.

Build it using `mvn clean package`.

Then, copy the jar `target/keycloak-run-sso.jar` in the camunda-bpm-run distro under `configuration/userlib`.

Alongside, copy all jars under `target/dependencies/*` to `configuration/userlib`.

### Prepare Keycloak

#### Optional: Use a local keycloak instance

For a head start, there is a keycloak docker-compose included. You can use it by running:

```shell
docker-compose up -d
```

Then, Keycloak will be available from `http://localhost:9090`.

#### Create a client

In the realm of your choice, create a client according to the docs presented [here](https://github.com/camunda-community-hub/camunda-platform-7-keycloak?tab=readme-ov-file#prerequisites-in-your-keycloak-realm).

>Make sure the client uses a refresh token for client credentials grant. This setting is under _Advanced_ of the client configuration.

Copy the client secret, you will need it soon.

#### Optional: Create group and user to start with

In the realm where the client lives, create a group `camunda-admin` and a user `demo`. Add `demo` to the group `camunda-admin` and set the password accordingly.

### Configure Camunda Run

Now, open the `default.yml` (or `production.yaml`) and add this section:

```yaml
keycloak:
  client-id: camunda
  client-secret: YfO3KUzAc5ScCbOzDijkdavmPWcRAOLv
  realm: master
  host: http://localhost:9090

plugin.identity.keycloak:
  keycloakIssuerUrl: ${keycloak.host}/realms/${keycloak.realm}
  keycloakAdminUrl: ${keycloak.host}/admin/realms/${keycloak.realm}
  clientId: ${keycloak.client-id}
  clientSecret: ${keycloak.client-secret}
  useEmailAsCamundaUserId: true
  administratorGroupName: camunda-admin

spring.security.oauth2:
  client:
    registration:
      keycloak:
        provider: keycloak
        client-id: ${keycloak.client-id}
        client-secret: ${keycloak.client-secret}
        authorization-grant-type: authorization_code
        redirect-uri: "{baseUrl}/{action}/oauth2/code/{registrationId}"
        scope: openid, profile, email
    provider:
      keycloak:
        issuer-uri: ${keycloak.host}/realms/${keycloak.realm}
        authorization-uri: ${keycloak.host}/realms/${keycloak.realm}/protocol/openid-connect/auth
        user-info-uri: ${keycloak.host}/realms/${keycloak.realm}/protocol/openid-connect/userinfo
        token-uri: ${keycloak.host}/realms/${keycloak.realm}/protocol/openid-connect/token
        jwk-set-uri: ${keycloak.host}/realms/${keycloak.realm}/protocol/openid-connect/certs
        # set user-name-attribute one of: 
        # - sub                -> default; using keycloak ID as camunda user ID
        # - email              -> useEmailAsCamundaUserId=true
        # - preferred_username -> useUsernameAsCamundaUserId=true
        user-name-attribute: email
```

In 7.21, we have to disable the Camunda Run own cors configuration, by default this setting is `true`:

```yaml
camunda:
  bpm:
    run:
      cors:
        enabled: false
```

Adjust the details like `keycloak.host`, `keycloak.realm`, `keycloak.client-id`, `keycloak.client-secret` and `plugin.identity.keycloak.administratorGroupName` accordingly.

Also, make sure the user id configuration matches. The config above uses the email as user id.

Then, make sure there is no admin user created. This is only required for the `default.yml`.

### Run it

After the setup is done, run Camunda Run by using the `./start.sh` script.

## Effect

The effect of this configuration should be that you are forwarded to Keycloak to sign in and then sent back to the Camunda webapps authenticated.