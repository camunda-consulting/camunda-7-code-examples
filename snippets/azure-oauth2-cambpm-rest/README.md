# azure-oauth2-cambpm-rest
Camunda BPM Spring Boot with Spring Security and Azure libraries to secure the Camunda REST API.

## Introduction

This project is a running example of a Camunda BPM Spring Boot instance using OAuth2 via Spring Security to authenticate a user against Azure Active Directory when accessing the Camunda REST API.  This project also makes use of Microsoft Azure AD Spring Boot Starter libraries.

## Prerequisites

You must have the following:

- An Azure account
- Access to Azure portal (portal.azure.com)
- Rights to create an Azure Postgresql database resource.
- You need to create a User in AAD (like 'user1')
- You need to be able to create Groups in AAD, and make your user a member of those groups as needed.  Make sure one of the groups is 'camunda-admin', as this is a default admin group in Camunda.
- You must be able to create an "App Registration" for your app (like 'spring-security-web-app')
- In that App Registration, you need to be able to generate a secret in the 'Certificates & Secrets' section
- In the Authentication section, you need to provide a Redirect URI.  
- under 'API Permissions', add permissions for the Default Directory of:
	- User.Read
	- Directory.AccessAsUser.All
	
Obviously learning all about Azure is beyond the scope of this project.  This is more of a Camunda BPM 'Quick Start' running example.

## Instructions

Once Prerequisites have been met, you must update the application.yaml with the templated values that you have gathered from your Azure setup.

For getting started, I must recommend this great article from Armando Montoya.

https://www.digitalonus.com/securing-a-restful-api-using-spring-boot-and-microsofts-azure-active-directory/

Go thru the following sections of his blog as noted:

**Configure an Active Directory instance**

**Register an application**

**Authentication section**

- make your redirect URI http://localhost:8081

**Manifest section**

**Exposing an API section**

**(SKIP THIS) Wiring up the Spring Boot project**

- you already have this app

**(SKIP THIS) Spring Boot app configuration**

- You already have a configured app.  Instead, you need to update your application.yaml file with your identifying information.

**(SKIP THIS) Running the application.**

- instead just compile and run your project.

**Implicit RESTful service testing with Postman**

- follow these instructions, then test with the URL http://localhost:8081/engine-rest/version
