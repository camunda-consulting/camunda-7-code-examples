# azure-oauth2-cambpm-web-4.5.0

Based on azure-oauth2-cambpm-web snippet

## Introduction

This project is a running example of a Camunda BPM Spring Boot instance using OAuth2 via Spring Security to authenticate a user against Azure Active Directory. 
This project is using Spring Cloud Azure version +4.0.0 and a newer Camunda version.

Main differences to older Azure AD example:

- Spring Could Azure is integrated with Spring Security (no security client configuration necessary)
- redirect-URI does not have /azure (security client name) suffix
- antMatchers in WebAppSecurityConfig are set to "/camunda/app/**" which requires an authentication even for the welcome page
- extra link configuration in config.js had to be adjusted, but is not necessary with the current setting of antMatchers

## Prerequisites

You must have the following:

- An Azure account
- Access to Azure portal (portal.azure.com)
- Rights to create an Azure Postgresql database resource.
- You need to create a User in AAD (like 'user1')
- You need to be able to create Groups in AAD, and make your user a member of those groups as needed.  Make sure one of the groups is 'camunda-admin', as this is a default admin group in Camunda.
- You must be able to create an "App Registration" for your app (like 'spring-security-web-app')
- In that App Registration, you need to be able to generate a secret in the 'Certificates & Secrets' section
- In the Authentication section, you need to provide a Redirect URI.  It should be something like http://localhost:8080/login/oauth2/code (when running this example locally)
- under 'API Permissions', add permissions for the Default Directory of:
	- User.Read
	- Directory.AccessAsUser.All
	
Obviously learning all about Azure is beyond the scope of this project.  This is more of a Camunda BPM 'Quick Start' running example.

## Instructions

Once Prerequisites have been met, you must update the application.yaml with the templated values that you have gathered from your Azure setup.

Then run 'mvn clean install'.  Then you can start your Spring Boot app locally with 'mvn spring-boot:run', or via your preferred IDE method.

Finally navigate to "http://localhost:8080".  You should get redirected to the Welcome screen.  Select the 'Login with your Microsoft Credentials' link, then use the credentials of the Azure AD user that you created to log in.  When complete, you should be logged in to Camunda as that user.

portal