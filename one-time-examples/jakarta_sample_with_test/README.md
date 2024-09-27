
# Sample JEE Project which use only java jakarta depedencies 

Generated with wildfly archetype 
```shell
mvn archetype:generate \     
    -DarchetypeGroupId=org.wildfly.archetype \
    -DarchetypeArtifactId=wildfly-getting-started-archetype

```

The `sample` project is a simple Jakarta EE application with a HTTP endpoint that is running in
[WildFly](https://wildfly.org).

The `src/main` folder contains a simple 'Hello world' style Jakarta EE application using JAX-RS.

## Building the application

To run the application, you use Maven:

```shell
mvn clean package
```


Maven will compile the application, provision a WildFly server
The WildFly server is created in `target/server` with the application deployed in it.

## Running the application

To run the application, run the commands:

```shell
cd target/server
./bin/standalone.sh
```

Once WildFly is running, the application can be accessed at `http://localhost:8080/`

You will reached the camunda cockpit under `http://localhost:8080/camunda`

Following Endpoints are available 

```shell
curl http://localhost:8080/hello/users
```
Return a list of users with their ids

```shell
curl http://localhost:8080/hello/{NamingOfFoo}
```

`NamingOfFoo` will create a user entry in the database, if it is already created, it will return the message `user is already created`

```shell
curl -X POST http://localhost:8080/engine-rest/process-definition/key/Process_Sample/start -H "Content-Type: application/json" '{"variables": {"user": {"value": "nom"}}}'
```
will create a new process instance in our sample application 

## Resources

* [WildFly](https://wildfly.org)
* [WildFly Documentation](https://docs.wildfly.org)
* [Camunda Documentation](https://docs.camunda.org/manual/latest/installation/full/jboss/manual/)