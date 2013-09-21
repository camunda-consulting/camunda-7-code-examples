package org.camunda.demo.camel.route;

import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import static org.camunda.bpm.camel.component.CamundaBpmConstants.*;
import org.camunda.demo.camel.dto.Order;
import org.camunda.demo.camel.processor.MapToOrderProcessor;
import org.camunda.demo.camel.processor.OrderToMapProcessor;

/**
 *
 * @author Nils Preusker - nils.preusker@camunda.com
 * @author Rafael Cordones - rafael@cordones.me
 *
 */
public class OpenAccountRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    /*
     * Get hot-folder configuration from a properties file
     *
     * TODO: maybe we can use http://camel.apache.org/using-propertyplaceholder.html
     */
    Properties properties = new Properties();
    properties.load(this.getClass().getClassLoader().getResourceAsStream("route.properties"));
    String ordersFolder = properties.getProperty("folder.orders");
    String postidentFolder = properties.getProperty("folder.postident");
    if(ordersFolder == null || postidentFolder == null) {
      throw new RuntimeException("could not read properties for camel route, make sure there" +
        " is a file called route.properties in the root or the resoureces folder of your " +
        "application that contains the properties folder.orders and folder.postident");
    }

    /*
     * There are two ways of starting the open-account process:
     *
     *   - placing an XML file in a hot folder (see route.properties for folder location)
     *   - sending a JSON order to a REST web service (see README.txt for instructions)
     */
    // This route handles files placed in the incoming orders folder (see route.properties file)
    // and routes the XML to a JMS queue:
    from("file://" + ordersFolder).
      routeId("hot-folder order route").
      log("=======================").
      log("Recieved order from hot-folder").
      log("=======================").
      to("jms:xmlQueue");

    // Order objects that are sent to the REST web service are placed in a JMS queue called
    // orderQueue (see README.txt about setting up the JMS queue). This route listens for
    // incoming messages on that queue, transforms the object to XML using the marshalling
    // feature that is built into camel and delivers the resulting XML to the same queue the
    // incoming XML documents are placed in:
    from("jms:orderQueue").
      routeId("order to xml route").
      log("=======================").
      log("received order from orderQueue").
      log("=======================").
      log("transforming order object to xml").
      log("=======================").
      marshal(new JaxbDataFormat(Order.class.getPackage().getName())).
      log("=======================").
      log("delivering order xml to xmlQueue").
      log("=======================").
      to("jms:xmlQueue");

    /*
     * This route listens for incoming messages on the JMS queue named xmlQueue. When a message
     * arrives, a CDI bean called incomingOrderService is used to extract the data from the XML
     * into a HashMap that will be passed on to the camunda BPM engine when the process instance
     * is started. The HashMap is the same as a variable map that can be passed on to process
     * instances via the camunda BPM API.
     */
    from("jms:xmlQueue").
      routeId("start order process route").
      log("=======================").
      log("received order xml from xmlQueue").
      log("=======================").
      log("setting order # to '" + CAMUNDA_BPM_PROCESS_DEFINITION_KEY + "' property").
      log("=======================").
      setProperty(CAMUNDA_BPM_PROCESS_DEFINITION_KEY).xpath("//@ordernumber").
      log("=======================").
      log("transforming order xml to order object").
      log("=======================").
      unmarshal(new JaxbDataFormat(Order.class.getPackage().getName())).
      log("=======================").
      log("transforming order object to variable map (java.util.Map) as input for the camunda BPM process").
      log("=======================").
      process(new OrderToMapProcessor()).
      log("=======================").
      log("starting open-account process").
      log("=======================").
      to("camunda-bpm:start?processDefinitionKey=open-account");

    /*
     * If the order was rejected, we'll send a mail to the customer to inform him that his
     * application will not be processed.
     */
    from("direct:inform-customer").
      routeId("inform customer route").
      beanRef("emailService");

    /*
     * When the order is approved, it can be passed to the accountService to create an account
     * object and persist it to the database. Since the order was split up into values in a
     * java.util.Map, we'll first pass the map to the MapToOrderProcessor.
     */
    from("direct:setup-account'").
      routeId("set up account route").
      log("=======================").
      log("transforming process variable map to order object: ${body}").
      log("=======================").
      process(new MapToOrderProcessor()).
      log("=======================").
      log("calling accountService to create account from incoming order").
      log("=======================").
      beanRef("accountService");

    /*
     * Finally, this route waits for incoming postident scans to be placed in a hot folder.
     * When a document is placed there (edit route.properties to configure the location of
     * the folder), the order number is extracted from the file name and used as correlation
     * id to send a signal to the waiting process instance.
     */
    from("file://" + postidentFolder).
      routeId("incoming postident route").
      log("=======================").
      log("received postident document").
      log("=======================").
      log("extracting order number from file name").
      log("=======================").
      process(new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
          String businessKey = exchange.getIn().getHeader("CamelFileName").toString().split("-")[1].substring(0, 4);
          exchange.setProperty("PROCESS_KEY_PROPERTY", businessKey);
        }
      }).
      log("=======================").
      log("correlating document with process instance").
      log("=======================").
      to("camunda-bpm://signal?processDefinitionKey=open-account:&activityId=wait_for_postident");
  }

}