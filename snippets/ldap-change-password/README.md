# Change the LDAP Password from Cockpit

In case users have to change their password regularly in Active Directory, these
two plugin show

- how to handle the return value, when a user tries to log in to Cockpit, but
  the engine returns an error from the LDAP server,
- and how to handle this in the frontend to show a link to a page where the user
  can change their password.

The server part is implemented in a process engine plugin in the
[active-directory-error-code-provider](active-directory-error-code-provider/).

The frontend part requires a Web application plugin as shown in the
[active-directory-frontend-plugin](active-directory-frontend-plugin/).

The example is tested against Active Directory.

The frontend plugin requires extension points that are available since Camunda
Platform 7.20-alpha4.
