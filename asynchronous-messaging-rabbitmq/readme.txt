# Introduction

# Environment Restrictions

# Remarks to run this application
There is no web interface to access the application. Ensure RabbitMQ is running.
To get started run the standalone ServiceProviderApplication and the
Arquillian test case, which by default connects to a camunda BPM Platform
running locally on JBoss AS 7.

# Known Issues
- The ResponseConsumer only processes one message after the application has been deployed.

# Improvements Backlog
