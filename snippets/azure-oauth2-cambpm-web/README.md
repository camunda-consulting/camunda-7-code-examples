# azure-oauth2-cambpm-web

## Introduction

This project is a running example of a Camunda BPM Spring Boot instance using OAuth2 via Spring Security to authenticate a user against Azure Active Directory.  This project also makes use of Microsoft Azure AD Spring Boot Starter libraries.

## Prerequisites

You must have an Azure account that can create a new tenant in Azure AD.

Obviously learning all about Azure is beyond the scope of this project. This is more of a Camunda BPM 'Quick Start' running example.

## Instructions

Use [this tutorial](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/configure-spring-boot-starter-java-app-with-azure-active-directory) provided by Microsoft to set up everything in Azure AD.
Then, go to the `application.yaml` file in this project and replace the client information also mentioned in the tutorial (there is no new parameter to add, just replace the place-holders)

Then run `mvn clean package` to get an executable jar in the `/target` directory that you can run.

Alternatively, start your Spring Boot app locally with `mvn spring-boot:run`, or via your preferred IDE method.

Finally navigate to [http://localhost:8080](http://localhost:8080).  You should get redirected to Azure AD login. When complete, you should be logged in to Camunda as that user.

## Limitation

This snippet does not cover a custom identity provider / LDAP identity provider. To set up a system containing "real" users, please make sure Camunda Platform 7 is able to retrieve user information via Azure AD as well.
