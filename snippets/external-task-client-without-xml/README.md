# External Task Worker without XML processing

After migrating the Java Enterprise Edition into the jakarta namespace (JEE 9),
some project struggle with the migration. When you have to run the latest
version Camunda 7.21 as an external task client on a JBoss 7.4 Server, you will
run into issues getting an exception:

```
java.lang.NoClassDefFoundError: jakarta/xml/bind/JAXBException
```

The reason for this exception is the usage of some jakarta classes to support
process variables serialized as XML.

This example shows how to build a custom external task client without the
dependency to the jakarta namespace.

It is useful, when you don't use process variables that are serialized as XML
and run your client as a war file on an JBoss 7.4 server.

## Custom external task client

The
[`custom-camunda-external-task-client`](custom-camunda-external-task-client/) is
build from the original external task client and removes the DomXmlDataFormat
and the DomXmlDataFormatProvider classes. These classes are used from Service
Provider Interface. The project overrides the
`META-INF/services/org.camunda.bpm.client.spi.DataFormatProvider` by removing
the reference to the DomXmlDataFormatProvider.

To build the jar file, run `mvn clean install` in this directory.

## Example external task worker

In the [example](basic-external-task-worker/), you can see the usage of the
library. 

This worker can be started in the IDE to test the environment.

## Testing

The external task client comes with an integration test, that tests the client
against a Camunda 7 engine running in Tomcat and deploys and start given process
definitions with different kind of variable handling.

This integration test is adopted in this branch of a fork from the
camunda-bpm-platform repository:
https://github.com/ingorichtsmeier/camunda-bpm-platform/tree/test-external-task-client-without-xml/clients/java

The branch contains the same changes as in this example and ignores the tests of
the XML variable handling.

Everything else runs successfully and proves, that the change did not harm other
features and functionalities.
