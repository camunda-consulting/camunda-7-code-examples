# camunda BPM example: Bank Account Opening using Mule ESB

**Table of Contents**  *generated with [DocToc](http://doctoc.herokuapp.com/)*

- [camunda BPM example: Bank Account Opening using Mule ESB](#camunda-bpm-example-bank-account-opening-using-mule-esb)
	- [What does it demonstrate?](#what-does-it-demonstrate)
	- [Technical environment](#technical-environment)
	- [The Process](#the-process)
	- [Getting Started](#getting-started)
	- [Starting a process instance via REST](#starting-a-process-instance-via-rest)
	- [Starting a process instance via XMl in drop folder](#starting-a-process-instance-via-xml-in-drop-folder)
	- [Correlate Postident PDF to process instance via drop folder](#correlate-postident-pdf-to-process-instance-via-drop-folder)
	- [Check Tasklist](#check-tasklist)
	- [The big picture as BPMN collaboration](#the-big-picture-as-bpmn-collaboration)
	
## What does it demonstrate?
- How to use [Mule ESB](http://www.mulesoft.org/) together with the [camunda BPM platform](http://camunda.org)
- How to correlate to a running process instance from Mule
- How to call (Mule) services from a process instance

## Technical environment
- [camunda BPM platform](http://camunda.org) (tested on JBoss AS 7)

## The Process

![Bank Account Opening][1]

A lot of banks provide their customers with an online form to sign up for a new bank account. However, before the bank account can officially be opened, the banks need to verify the person's identity. The methods of identity verification vary from country to country, one commonly used term being "notarized letter", which means that a letter is signed by an official witness (e.g. a lawyer) who verifies a person's identity. In Germany, this service is called "postident" and is provided by post offices. That's what we'll be using in this showcase.

The BPMN diagram shows the resulting process with all it's technical ins and outs. Orders to open a bank account can be submitted through the website or a legacy system like e.g. FTP. The website produces Java objects while the orders arrive as XML documents on the FTP server.

Since the management is interested in the time it typically takes from the initial creation of a bank account until the identity documents arrive, we decided to implement those steps as an executable business process. This will automatically store the time stamps of each step in a database and thus make it easy to write some monitoring and reporting later on. However, there are also some data transformation steps before a process can be started and we need to poll folders for incoming files. These are tasks that Mule ESB is really good at, so we decided to combine the process engine with Mule ESB.

Last but not least, it is crucial that all incoming orders are accepted and processed. While the incoming XML orders are already stored in the file system, we'll write the orders that come in from the website to a JMS queue (actually to showcase JMS ;-)).

## Getting Started

- Download the [camunda BPM platform](http://camunda.org/) for JBoss AS 7 **(tested on 7.0.0-alpha6)** from [here](http://camunda.org/download.html) and install it.
- Add JMS Queues to your server, therefor edit the standalone-full.xml

```xml
<jms-queue name="orderQueue">
	<entry name="queue/order"/>
    <entry name="java:jboss/exported/jms/queue/order"/>
</jms-queue>
<jms-queue name="xmlQueue">
	<entry name="queue/xml"/>
    <entry name="java:jboss/exported/jms/queue/xml"/>
</jms-queue>
```

  You might not yet have any queues in your JBoss - in this case add a `<jms-destinations>` as last element within the `<hornetq-server>` element:

```xml
<hornetq-server>
    ....
    <jms-destinations>
		<jms-queue name="orderQueue">
			<entry name="queue/order"/>
		    <entry name="java:jboss/exported/jms/queue/order"/>
		</jms-queue>
		<jms-queue name="xmlQueue">
			<entry name="queue/xml"/>
		    <entry name="java:jboss/exported/jms/queue/xml"/>
		</jms-queue>
	</jms-destinations>
</hornetq-server>	
```

- Configure your JBoss to use the **full profile (we use JMS in this demo)**, therefor start the server by by typing `./standalone.sh -c standalone-full.xml` (Linux/Unix/Mac) or `standalone.bat -c standalone-full.xml` (Windows) in `<CAMUNDA_BPM_PLATFORM_HOME>/server/jboss-as-7.1.3.Final/bin`
- Make sure you have the following installed *and working*:
    * [Java Platform (*JDK*) 1.6.x](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    * [Maven](http://maven.apache.org/) 3.0.x
    * [Git](http://git-scm.com/) 1.7.x
- Clone this repository
- Rename `src/main/resources/route.properties.example` to `src/main/resources/route.properties` and open it with a text editor. Replace the various folder properties with the directories you would like to use as hot-folders for the showcase.
- Build the application with `mvn package`
- Copy the generated WAR artifact to the JBoss AS 7 deployment directory `<CAMUNDA_BPM_PLATFORM_HOME>/server/jboss-as-7.1.3.Final/standalone/deployments/`
- Ready! You should see no errors in the server log.
- Now you can wither start the open account process by placing an XML file in the orders hot-folder or sending a JSON order to the REST web-service.



## Starting a process instance via REST

Use your favorite REST/HTTP client (curl, chrome rest client, cocoa rest client to name a few) to place a HTTP PUT request:

- method: `PUT`
- URL: [http://localhost:8080/bank-account-opening-mule/rest/orders/](http://localhost:8080/bank-account-opening-mule/rest/orders/)
- Header: `Content-Type: application/json`
- Request Body (example): 

```  
{
	"accounttype": "saving",
	"address": {
			"city": "The City",
			"country": "The Country",
			"number": 1,
			"state": "The State",
			"street": "The Street",
  			"zipcode": "1234"},
	"ordernumber": "0001",
	"person": {
		"dateofbirth": "2012-08-16T13:26:38.551Z",
		"email": "john.doe@somecompany.com",
		"firstname": "John",
		"gender": "male",
		"lastname": "Doe",
		"phonenumber": "123456",
		"placeofbirth": "Some Place",
 		"title": "Mr."}
}
```

If you use CURL you might want to use this:

```
curl --header "Content-Type: application/json" -X PUT -vd "{\
 \"person\" : {\
 \"gender\" : \"male\",\
 \"lastname\" : \"Doe\",\
 \"firstname\" : \"John\",\
 \"title\" : \"Mr.\",\
 \"email\" : \"john.doe@somecompany.com\",\
 \"placeofbirth\" : \"Some Place\",\
 \"phonenumber\" : \"123456\",\
 \"dateofbirth\" : 1345123598551\
 },\
 \"address\" : {\
 \"street\" : \"The Street\",\
 \"number\" : 1,\
 \"city\" : \"The City\",\
 \"country\" : \"The Country\",\
 \"zipcode\" : \"1234\",\
 \"state\" : \"The State\"\
 },\
 \"accounttype\" : \"saving\",\
 \"ordernumber\" : \"0001\"\
}" http://localhost:8080/bank-account-opening-mule/rest/orders/
```



## Starting a process instance via XMl in drop folder 

Remember: You configured the according folder in the `route.properties` file. You can use this example as a basis:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<order ordernumber="0001">
	<person>
		<firstname>John</firstname>
		<lastname>Doe</lastname>
		<title>Mr.</title>
		<dateofbirth>2012-04-27T12:30:23.998+02:00</dateofbirth>
		<placeofbirth>Somewhere</placeofbirth>
		<gender>male</gender>
		<phonenumber>123456</phonenumber>
		<email>john.doe@company.com</email>
	</person>
	<address>
		<street>The Street</street>
		<number>1</number>
		<zipcode>1234</zipcode>
		<city>The City</city>
		<state>The State</state>
		<country>The Country</country>
	</address>
	<accounttype>debit</accounttype>
</order>
```



**Order Number as Correlation ID:** Note that we are using the "oredernumber" attribute to correlate the "postident" document with the initial order. That means that the order number has to be unique for each submitted bank account order. Orders with existing order numbers will be accepted by the web-service but end up in a dead letter queue.




## Correlate Postident PDF to process instance via drop folder 

In order to move on the process instance waiting for postident

- Copy a file named `something-0001.pdf` to the drop folder you configred for postident. The `0001` must match the order number you set when creating the process instance.


## Check Tasklist

Now you can check the task created in the [camunda Tasklist](http://localhost:8080/tasklist/). 

You can approve or not approve the account - check the server logs for the according actions. 


## The big picture as BPMN collaboration

![Bank Account Opening][2]


[1]: https://raw.github.com/camunda/camunda-consulting/master/showcases/bank-account-opening-mule/src/main/webapp/resources/img/bpmn-overview-bank-acount-opening.png
[2]: https://raw.github.com/camunda/camunda-consulting/master/showcases/bank-account-opening-mule/src/main/webapp/resources/img/bpmn-collaboration-bank-acount-opening.png
