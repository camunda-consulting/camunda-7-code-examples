# Introduction

# Environment Restrictions

# Remarks to run this application
First ensure that the following software is running on your machine:
camunda BPM platform on JBoss AS 7: http://www.camunda.org/download.html
RabbitMQ: http://www.rabbitmq.com/download.html

There is no web interface to access the application. To get started run the
ServiceProviderApplication as a Java Application in Eclipse and the
ArquillianTest as a JUnit Test, which by default connects to a camunda BPM Platform
running locally  .

# Known Issues
- The ResponseConsumer only processes one message after the application has been deployed.

# Improvements Backlog
- Use JMS MDB for Callback Consumer: http://www.lshift.net/blog/2009/03/16/openamqs-jms-client-with-rabbitmq-server
