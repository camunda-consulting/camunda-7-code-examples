# How to start tenants dynamically using shared process engines and process applications

Table of Contents

- [What does it demonstrate?](#what-does-it-demonstrate)
- [Technical environment](#technical-environment)
- [Getting Started](#getting-started)
	
	
## What does it demonstrate?

camunda BPM provides a lot of infrastructure to run a shared process engine and deploy multiple process applications containing the processes. The normal approach is to define all process engines statically in the bpm-platform.xml or processes.xml. See http://docs.camunda.org/latest/guides/user-guide/#process-applications and http://docs.camunda.org/latest/api-references/deployment-descriptors/#descriptors.

The requirement now maybe that you want to start engines **dynamically** during runtime. Now things get a bit more tricky - since the camunda BPM magic does not register the existing Process Applications for newly created Process Engines during runtime.

But this can be achieved in multiple ways. One possibility - showcased in this example - is to use a default Process Engine as reference - hence all processes are always deployed and registered there - even if it is not used. Then when starting Process Engines for tenants we can always ask the default engine to provide us the information we need.

This can be shown using a simple example:

![Overview of Showcase][1]

[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/dynamic-multi-tenancy/tenant.png

The tenant manager exposes a WebService via http://localhost:8080/tenantdemo-tenant-manager/ManagementWS?wsdl - this allows to create new tenants on the fly.

## Current limitations
- Tenants are stored in a static field in the TenantManager, hence new tenants will vanish after restarting the server
- The demo is designed for Tomcat and due to JAX-WS packaging limited to it.
- Adding new process definitions on-the-fly was a requirement as well - this is not yet shown
- The process application are not yet de-registered during shutdown of the engines or process applications - this should be added


## Technical environment
- camunda BPM platform 7.1.0
- Tomcat


## Getting Started

* Clone this repo and build the projects using Maven
* copy the shared lib into the Tomcat shared lib directory
* Deploy the  WARs on the container (*tested only with Tomcat 7*)

You should see some logging like this:

 Started process engines for tenants - now running:
 [default, tenant1, tenant3, tenant2]


## Known Issues

This is a pretty rough example hacked in a couple of minutes in the train. So it is neither a really sophisticed nor the guranteed best solution for this approach! 

Feel free to discuss it in our forum: http://camunda.org/community/forum.html
