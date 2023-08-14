# Frontend Plugin for Changing the LDAP Password

This frontend plugin handles the return code from the
active-directory-error-code-provider and shows a snippet with a link to a page
to change your LDAP password.

The important JavaScript files are located under [frontend/src/](frontend/src/).

## Implementation details

[plugin.js](frontend/src/plugin.js) exports the plugin to the framework with the
plugin points `cockpit.login` and `cockpit.login.data`.

[password-policy.js](frontend/src/password-policy.js) implements the logic for
handling the result from the server plugin and rendering a page with a link to
change the LDAP password.

The `result` function checks the response from the
active-directory-error-code-provider and hides the sign in-form and shows the
reset password-section instead. The values to check against have to be equal to
the return code from the process engine plugin. See
[ActiveDirectoryExceptionCodeProvider.java](../active-directory-error-code-provider/src/main/java/org/camunda/bpm/plugin/activedirectory/ActiveDirectoryExceptionCodeProvider.java)
how the values are created.

The `render` function registers the html code with the reset password section
and hides it initially.

[reset-password.html](frontend/src/reset-password.html) contains the HTML
snippet that is shown when a user tries to login and his password is expired.

The plugin uses [rollup.config.js](frontend/rollup.config.js) to distribute the
plugin and the policy with the html-snippet to all applications: welcome, admin,
tasklist and cockpit.

## Build and install

Build the frontend plugin with `mvn package` and move the jar
`active-directory-frontend-plugin-1.0-SNAPSHOT.jar` into the
`configuration/userlib` folder of your Camunda Run installation.

The JavaScript part gets build with `npm` from maven automatically.

## General documentation

More details about Webapp plugin development and installation can be found here:
https://docs.camunda.org/manual/latest/user-guide/camunda-bpm-run/
