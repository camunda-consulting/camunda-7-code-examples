## Standalone Web-Apps with Spring Boot and embedded reverse proxy

This project shows an example of how to operate a standalone Web App based on Spring Boot.

If one needs to separate the Web-Apps from the actual Process Application, a few things need to be considered when using Camunda Tasklist.

### Running Java Code as asynchronous jobs

One has to make sure that delegation code gets executed within the Process Application. This means that after each user interaction we need to switch responsibility from the Tasklist (client) thread to the job executor of the node where the delegation code is deployed.

Two things are important:

- One needs to add the appropriate transaction boundaries (async after)
- The job executor needs to be set to be deployment aware

### Embedded forms

Since the Tasklist runs in its own Spring Boot Application, it will not have direct access to embedded forms of the process application. This can be achieved using a reverse proxy to resolve the form key. In my example, I use Zuul as an embedded reverse proxy.

In more traditional architectures, where the Standalone Webapp is deployed as a war file, one can also choose to use an external reverse proxy to do the lookup.