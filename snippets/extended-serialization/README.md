Extended serialization
=========================

If you use libraries like Joda Money in your project you need some special setup to deserialize your Money-objects from JSON.

This setup may help you to overcome the exceptions like

    org.camunda.bpm.engine.ProcessEngineException: Cannot deserialize object in variable 'product': SPIN/JACKSON-JSON-01006 Cannot deserialize ...
 
and 

    Caused by: spinjar.com.fasterxml.jackson.databind.JsonMappingException: No suitable constructor found for type [simple type, class org.joda.money.Money]: can not instantiate from JSON object (need to add/enable type information?)
  
Implementation
--------------

One step is to provide implementations of a serializer and deserializer for the Joda Money type. You can find examples [here](extended-serialization-plugin/src/main/java/com/camunda/consulting/extendedSerialization/serializer). These serializer and deserializer write an onject of Money as 

    {"price":"EUR 34.99"}
     
and create a Java object from the json represetation.
  
You must register these serializers in a new implementation of a DataFormatConfigurator from the type JacksonJsonDataFormat like the [JodaDataFormatConfigurator](extended-serialization-plugin/src/main/java/com/camunda/consulting/extendedSerialization/configuration/JodaDataFormatConfigurator.java)   

The JodaDataFormatConfugurator is published as a service provider (SPI) in the file [org.camunda.spin.spi.DataFormatConfigurator](extended-serialization-plugin/src/main/resources/META-INF/services/org.camunda.spin.spi.DataFormatConfigurator). The file is saved in the META-INF/services directory and contains the fully qualified class path.

This project includes serializers and deserializers for joda time, too.

Packaging
---------

This project has two submodules. extended-serialization-plugin 
extends the process engine so it has be installed on the same level as the process engine. For a JBoss installation with a shared engine (like our distribution) the project has to be packaged as a JBoss module and must be installed in the JBoss modules folder.

The module can be packaged with a maven plugin from [http://www.smartics.eu/smartics-jboss-modules-maven-plugin/](http://www.smartics.eu/smartics-jboss-modules-maven-plugin/). The plugin is used as a profile and `mvn package attach` builds the module with jars and module.xml-files in target/jboss-modules. Check the profile-part of the pom.xml for further configuration details.

The module itself is configured in the [extended-serializer-module.xml](extended-serialization-plugin/src/main/resources/META-INF/jboss-modules/extended-serialization-module.xml) in the folder META-INF/jboss-modules. The configuration includes a mapping of maven coordinates of joda-time:joda-time to the module org.joda.time:2.1.

Installation of the module
--------------------------

Now you must copy some of the modules from the target folder to your JBoss installation. The com.camunda.consulting.extended-serialization folder is the implementation of the configurator. It is dependent of the modules org.joda.joda-money and org.joda.time. org.joda.joda-money must be copied to the modules folder, too. org.joda.time is still there from the distribution.

The extended-serialization module must be registered in the org.camunda.bpm.jboss.camunda-jboss-subsystem module.xml and in the org.camunda.bpm.engine-plugin-spin module.xml with its name:

    <module name="com.camunda.consulting.extended-serialization" services="import"/>  

The services attribute hints to the JEE services SPI.
 
As the project contains a process engine plugin too, you have to configure this plugin in the engine in the standalone.xml

    <plugin>
      <class>com.camunda.consulting.extendedSerialization.plugins.SerializerProcessEnginePlugin</class>
    </plugin> 
    
The JBoss server starts without errors and you can find these lines in your log file:

    INFO  [org.camunda.spin] (ServerService Thread Pool -- 50) SPIN-01011 Discovered Spin data format configurator: class com.camunda.consulting.extendedSerialization.configuration.JodaDataFormatConfigurator[dataformat = org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat]
    INFO  [com.camunda.consulting.extendedSerialization.configuration.JodaDataFormatConfigurator] (ServerService Thread Pool -- 50) Configure Money serializer and deserializer
    INFO  [com.camunda.consulting.extendedSerialization.configuration.JodaDataFormatConfigurator] (ServerService Thread Pool -- 50) additional serializers added to object mapper

    INFO  [com.camunda.consulting.extendedSerialization.plugins.SerializerProcessEnginePlugin] (ServerService Thread Pool -- 50) postProcessEngineBuild
    
    
Packaging of process applications
---------------------------------

A process application that uses this dataformat configurator can be found in the sub module [extended-serialization-process](extended-serialization-process)

For the class loading you have to set the artifacts that are used by the DataFormatConfigurator to the scope provided. They must not be packaged into the war, otherwise the serialization will not use your custom serializer and deserializer. 

And to make JBoss to search in the modules for the serializer, you have to add the dependency in to MANIFEST.MF like this line:

    Dependencies: org.joda.joda-money, org.joda.time:2.1
  

How to use it?
--------------

Install the modules on your JBoss camunda bpm distribution. Install the process application extended-serialization-process. Log into the tasklist and start a process. Complete the user task and check the server log for the output of the second service task. 

If you want to access the complex objects via REST-API, always attach `?deserializeValue=false` to the GET request:

    http://localhost:8080/engine-rest/variable-instance/a8b8f2f0-c1d7-11e4-acbc-1abf20524153?deserializeValue=false
    
returns:

    {
      type: "Object"
      value: "{"name":"exampleName","price":"EUR 34.99","purchaseDate":null}"
      valueInfo: {
        serializationDataFormat: "application/json"
        objectTypeName: "com.camunda.consulting.extendedSerializationProcess.ComplexDataObject"
      }
      id: "a8b8f2f0-c1d7-11e4-acbc-1abf20524153"
      name: "complexValue"
      processInstanceId: "a89bf50c-c1d7-11e4-acbc-1abf20524153"
      executionId: "a89bf50c-c1d7-11e4-acbc-1abf20524153"
      caseInstanceId: null
      caseExecutionId: null
      taskId: null
      activityInstanceId: "a89bf50c-c1d7-11e4-acbc-1abf20524153"
      errorMessage: null
    }
    
Otherwiese the REST client will try to serialize the Json-Object again without access to the dataFormatConfigurator. Result will be an error like the one from the start of this readme...    

Environment Restrictions
------------------------

Built and tested against Camunda BPM version 7.2.0. Distribution camunda-bpm-jboss-7.2.0


Known Limitations
-----------------

