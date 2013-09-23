package org.camunda.demo.camel.route;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.seda.SedaComponent;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camunda.bpm.camel.component.CamundaBpmComponent;
import org.camunda.bpm.camel.component.CamundaBpmEndpoint;
import org.camunda.bpm.camel.component.producer.CamundaBpmProducer;
import org.camunda.demo.camel.OpenAccountRouteBuilder;
import org.camunda.demo.camel.business.AccountService;
import org.camunda.demo.camel.business.EmailService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


/**
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 * @author Rafael Cordones - rafael@cordones.me
 *
 */
public class OpenAccountRouteTest extends CamelTestSupport {

	// This is a flag to know whether the camunda BPM producer
	// was called. See comments below for details.
	static boolean invoked = false;
	
	private static String ordersFolder;
	private static String postidentFolder;

	private CamundaBpmProducer producer;

	@BeforeClass
	public static void setUpFromPropertiesFile() throws IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File("src/test/resources/route.properties")));
		ordersFolder = properties.getProperty("folder.orders");
		postidentFolder = properties.getProperty("folder.postident");
		if (ordersFolder == null || postidentFolder == null) {
			throw new RuntimeException(
					"could not read properties for camel route, make sure there"
							+ " is a file called route.properties in the root or the resoureces folder of your "
							+ "application that contains the properties folder.orders and folder.postident");
		}
	}

	@Override
	protected JndiRegistry createRegistry() throws Exception {
		JndiRegistry registry = super.createRegistry();

		EmailService emailService = mock(EmailService.class);
		AccountService accountService = mock(AccountService.class);

		registry.bind("emailService", emailService);
		registry.bind("accountService", accountService);

		return registry;
	}
	
	@Override
	protected CamelContext createCamelContext() throws Exception {
		// start off by letting CamelTestSupport create the context 
		CamelContext context = super.createCamelContext();

		// Stub the jms component by replacing it with seda
		context.addComponent("jms", context.getComponent("seda", SedaComponent.class));
		
		// Add a mock that will act as the camunda BPM component. We'll actually use this
		// to verify the behavior of our route later.
		CamundaBpmComponent component = mock(CamundaBpmComponent.class);
		CamundaBpmEndpoint endpoint = mock(CamundaBpmEndpoint.class);
		when(endpoint.getEndpointKey()).thenReturn("camunda:open-account");
		when(endpoint.getEndpointUri()).thenReturn("camunda:open-account");
		when(component.createEndpoint(anyString())).thenReturn(endpoint);
		when(component.getCamelContext()).thenReturn(context);
		when(endpoint.getCamelContext()).thenReturn(context);
		producer = mock(CamundaBpmProducer.class);
		// This is the slightly tricky part, we need some kind of feedback when the 
		// camunda BPM Camel component's producer is invoked. This will let us know that
		// the routes were successfully executed up to the process start. And yes,
		// we're misusing the doAnswer method of mockito here...
		doAnswer(new Answer<String>() {
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				invoked = true;
				return null;
			}
		}).when(producer).process((Exchange)anyObject());
		when(endpoint.createProducer()).thenReturn(producer);
		// and finally we add the mocked camunda BPM component to the camel context
		context.addComponent("camunda-bpm", component);
		
		return context;
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		// add open account routes to the test context
		return new OpenAccountRouteBuilder();
	}

	@Test
	public void testFirstRoute() throws Exception {
		// create a notify builder (see http://camel.apache.org/notifybuilder.html) to get notified when the hot-folder order 
		// route is executed
		NotifyBuilder jmsXmlQueueNotify = new NotifyBuilder(context).fromRoute("hot-folder order route").whenReceived(1).create();
		// use camel's producer template to send a file to the file endpoint
		template.sendBody("file://" + ordersFolder,
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
						+ "<order ordernumber=\"0001\">"
						+ "  <person>"
						+ "    <firstname>John</firstname>"
						+ "    <lastname>Doe</lastname>"
						+ "    <title>Mr.</title>"
						+ "    <dateofbirth>2012-04-27T12:30:23.998+02:00</dateofbirth>"
						+ "    <placeofbirth>Some Place</placeofbirth>"
						+ "    <gender>male</gender>"
						+ "    <phonenumber>123456</phonenumber>"
						+ "    <email>john.doe@somecompany.com</email>"
						+ "  </person>" + "  <address>"
						+ "    <street>The Street</street>"
						+ "    <number>1</number>"
						+ "		<zipcode>1234</zipcode>"
						+ "    <city>The City</city>"
						+ "    <state>The State</state>"
						+ "    <country>The Country</country>" 
						+ "  </address>"
						+ "  <accounttype>debit</accounttype>" 
						+ "</order>"
				);
		// Verify the expression of the notify builder matches within a wait interval of 10 seconds
		assertTrue(jmsXmlQueueNotify.matches(10, TimeUnit.SECONDS));
		// This is it, now wait for the camunda BPM producer to be invoked
		while(!invoked) {
			Thread.sleep(500);
		}
		// And even though we already know, we'll use mockito to verify the producer was really really called ;)
		verify(producer, times(1)).process((Exchange)anyObject());
	}

}
