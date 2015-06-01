Process Application with embedded engine in JEE
===============================================

These projects include a process application with an embedded engine for a JEE application server. It is packed as an ear file with the process application as war included.

The ear includes the libraries for the engine, the connection adapter to access the thread pool for the job executor and an ejb library with the process engine services.

Goal
----

Deploy the ear file on a plain vanilla Oracle Weblogic Server. The Server must only be prepared with a datasource for the process engine. 

Steps to pack the process engine into an ear-file together with the process application:

1. repackage the camunda-oracle-weblogic-rar
2. repackage the camunda-oracle-weblogic-service
3. repackage the camunda-engine-spring
4. configure the ear file
5. use the camunda-archetype-ejb-war archetype to build the process application 

Configure the job executor rar
------------------------------

To use the job executor rar in an embedded engine in a weblogic ear file, you have to add the classpath entries pointing to the engine libraries into the rar-file. Have a look at the pom.xml to see how the shading of artifact adds a new MANIFEST.MF with a Class-Path entry.

Configure the services jar
--------------------------

The service jar starts the process engine and uses the job executor rar. It has to include a MANIFEST.MF with a Class-Path entry. 

It includes the bpm-platform.xml for the configuration of the engine. This is the place to add more plugins.

Configure camunda-engine-spring.jar
-----------------------------------

The camunda-engine-spring.jar has a SPI to the ProcessApplicationElResolver. This entry must be removed and the remaining jar must be part of the process engine library. Have a look at the pom.xml.

Configure the ear file
----------------------

The ear file is controlled by the pom.xml. It includes the engine libraries in a lib folder. The sequence to start the modules is controlled by the initializeInOrder configuration in the pom (and therefore in the application.xml).

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-ear-plugin</artifactId>
        <version>2.6</version>
        <configuration>
          <version>6</version>
          <defaultLibBundleDir>lib</defaultLibBundleDir>
          <initializeInOrder>true</initializeInOrder>
        </configuration>
      </plugin>

The ear file includes the libraries in a lib folder and the modules for the jobexecution rar, service jar, the rest api war, the camunda webapp war and the process application war. All the modules are configured in the maven-ear-plugin element of the pom.xml.   

          <modules>
            <rarModule>
              <groupId>com.camunda.consulting</groupId>
              <artifactId>embedded-oracle-weblogic-rar</artifactId>
            </rarModule>
            <ejbModule>
              <groupId>com.camunda.consulting</groupId>
              <artifactId>jndi-printer-ejb</artifactId>
            </ejbModule>
            <ejbModule>
              <groupId>com.camunda.consulting</groupId>
              <artifactId>embedded-oracle-weblogic-service</artifactId>
            </ejbModule>
            <webModule>
              <groupId>com.camunda.consulting</groupId>
              <artifactId>process-application-war</artifactId>
            </webModule>
            <webModule>
              <groupId>org.camunda.bpm</groupId>
              <artifactId>camunda-engine-rest</artifactId>
              <contextRoot>/engine-rest</contextRoot>
            </webModule>
            <webModule>
              <groupId>org.camunda.bpm.webapp</groupId>
              <artifactId>camunda-webapp-ee-wls</artifactId>
              <contextRoot>/camunda</contextRoot>
            </webModule>
          </modules>

The camunda-engine-spring.jar is excluded from the lib-folder. We use the shaded jar instead.

          <packagingExcludes>lib/camunda-engine-spring*.jar</packagingExcludes>
    
An additional module shows all JNDI-names available in the different Contexts. It is useful for debugging purpose and not needed to run the process engine.

Deployment
----------

Go to the main-folder of the project `embedded-jee`. Run

    mvn package  

Copy the ear file from the target folder to the autodeploy folder from the weblogic domain. Or configure and use the maven plugin that is delivered with the weblogic server installation. See [the Oracle documentation](http://docs.oracle.com/cd/E24329_01/web.1211/e24368/maven.htm#WLPRG585).
