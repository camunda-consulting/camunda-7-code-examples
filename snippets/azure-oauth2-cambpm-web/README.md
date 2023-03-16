# azure-oauth2-cambpm-web

## Introduction

This project is a running example of a Camunda BPM Spring Boot instance using OAuth2 via Spring Security to authenticate a user against Azure Active Directory. 
This project is using Spring Cloud Azure version +4.0.0 and Camunda 7.18.0.

Spring Cloud Azure version +4.0.0 is integrated with Spring Security and no extra Spring Security Client is configured.

## Prerequisites

You must have the following:

- An Azure account
- Access to Azure portal (portal.azure.com)
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