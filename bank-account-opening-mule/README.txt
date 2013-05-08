For an overview of the process we automate in this showcase have a look at the related tutorial: https://app.camunda.com/confluence/display/foxUserGuide/Bank+Account+Opening

1. Setting up the JMS queues

You'll need to set up two JMS queues before starting the showcase: 'xmlQueue' and 'orderQueue'

If you have JMS set up in your JBoss AS 7, all you have to do is add the following XML to '<jms-destinations>':

<jms-queue name="orderQueue">
	<entry name="queue/order"/>
    <entry name="java:jboss/exported/jms/queue/order"/>
</jms-queue>
<jms-queue name="xmlQueue">
	<entry name="queue/xml"/>
    <entry name="java:jboss/exported/jms/queue/xml"/>
</jms-queue>

You might not yet have any queues in your JBoss - in this case typically add 
a '<jms-destinations>' as last element within the '<hornetq-server>' element:

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

2. Use it

A) Calling REST web service

Use your favorite REST/HTTP client (curl, chrome rest client, cocoa rest client to name a few) to place a HTTP PUT request:
  
METHOD: PUT
URL: http://localhost:8080/bank-account-opening-mule/rest/orders/
HEADER: Content-Type: application/json
Request Body (example): 
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

B) Drop XML file into folder "orders", which you have to define in the route.properties file. You can use this example as a basis:

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<order ordernumber="0815">
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