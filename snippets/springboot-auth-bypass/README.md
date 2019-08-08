# Bypassing the IdentityService when using Authentication

This project shows how one can bypass IdentityService queries when doing the authentication.

A possible use case could be that you have the LDAP plugin configured but want to configure special users that are always able to access the web apps or the rest api, even if they are not known to the LDAP or if the LDAP is not available.

*Warning:* This code intercepts some of Camunda's existing security mechanism and therefore might potentially weaken the security of your application! Do carefully review the code and adjust it to your needs before using any of this in production.