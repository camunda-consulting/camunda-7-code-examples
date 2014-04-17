package org.camunda.demo.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.camunda.bpm.engine.delegate.BpmnError;

/**
 * Adding the Routes via Java DSL here
 * 
 * @author Bernd Ruecker
 */
public class MyCamelRouteBuilder extends RouteBuilder { 

  public static String dropFolder = System.getProperty("user.home") + System.getProperty("file.separator") + "camunda-bpm-demo-camel";
  
  public static String consumerKey = "lRhS80iIXXQtm6LM03awjvrvk";
  public static String consumerSecret = "gabtxwW8lnSL9yQUNdzAfgBOgIMSRqh7MegQs79GlKVWF36qLS";
  public static String token = "220324559-jet1dkzhSOeDWdaclI48z5txJRFLCnLOK45qStvo";
  public static String tokenSecret = "B28Ze8VDucBdiE38aVQqTxOyPc7eHunxBVv7XgGim4say"; 


  @Override
  public void configure() throws Exception {    
    // ################################
    // Drop folder starts via none start event
    from("file://" + dropFolder) // use drop folder
      .routeId("file") //
      .convertBodyTo(String.class) //
      .to("log:org.camunda.demo.camel?level=INFO&showAll=true&multiline=true") // logging
      .to("camunda-bpm:start?processDefinitionKey=camel-use-cases"); // and start process instance

    
    String lastTweetId = "382404110550437888";
    // ################################
    // Twitter starts via message event
    from("twitter://search?sinceId="+lastTweetId+"&type=polling&delay=5&keywords=camunda&consumerKey="+consumerKey+"&consumerSecret="+consumerSecret+"&accessToken="+token+"&accessTokenSecret="+tokenSecret) //
      .routeId("twitter") //
      .to("log:org.camunda.demo.camel?level=INFO&showAll=true&multiline=true") // logging
      .transform().groovy("def map=[:] \n" +
          "map['tweet.what'] = request.body.text       \n" +
          "map['tweet.when'] = request.body.createdAt  \n" +
          "request.body = map")
      .to("camunda-bpm:message?messageName=camel.start"); // and start process instance

    // ################################
    // Synchronous Service calles from process
    from("direct://syncService") // service name in memory
      .routeId("syncService") //
      .to("log:org.camunda.demo.camel?level=INFO&showAll=true&multiline=true") // logging
      .onException(SampleException.class) // map exception to BPMN error
        .throwException(new BpmnError("camel.error")) //
        .handled(true) // TODO: Check how we can avoid logging on console
      .end()
      .process(new Processor() {        
        @Override
        public void process(Exchange exchange) throws Exception {
            // always throwing error to demonstrate error event
           throw new SampleException("some error occured in service");
        }
      });

    
    // ################################
    // Asynchronous Service (triggering message callback)
    from("direct://asyncService") // service name
      .routeId("asyncService") //
      .to("seda:someQueue?waitForTaskToComplete=Never");
    
    // now some external service would do some magic
    // then send response
    from("seda:someQueue")
      .routeId("asyncResponse") //
      .process(new Processor() {
        @Override
        public void process(Exchange arg0) throws Exception {
          // wait a bit to see that it is async
          for (int i = 0; i < 20; i++) {
            System.out.println("...zzzzzz...");
            Thread.sleep(100);
          }
        }
      })
      .to("camunda-bpm:message?messageName=camel.answer");    
  }

}
