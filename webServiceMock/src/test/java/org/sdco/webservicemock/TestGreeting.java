package org.sdco.webservicemock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.xml.ws.Endpoint;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import static org.mockito.Mockito.reset;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Unit test for simple App.
 */
public class TestGreeting     
{
	final static String helloWorldUrl = "http://localhost:9000/helloWorldService";
	static HelloWorld helloWorldServiceMock; 
	static ArgumentCaptor<String> hiCaptor;
	static ArgumentCaptor<String> goodbyeCaptor;
	 
	@Before
	public  void startup() throws Exception {
		reset(helloWorldServiceMock);
	
		
	}
	@BeforeClass
	public  static void startupClass() throws Exception {
		helloWorldServiceMock = mock(HelloWorld.class);		
		 

		 HelloWorld helloWorldService = (HelloWorld)    ServiceProxy.newInstance(helloWorldServiceMock);
		 
		 Endpoint helloWorldServiceEndpoint = Endpoint.publish(helloWorldUrl, helloWorldService);
		 hiCaptor = ArgumentCaptor.forClass(String.class);;
		 goodbyeCaptor = ArgumentCaptor.forClass(String.class);;
	
		}
	
	@Test
    public void TestGreetings()
    {
		when(helloWorldServiceMock.askName("first")).thenReturn("richard");
		when(helloWorldServiceMock.askName("last")).thenReturn("feynman");

		BusinessClass businessClass = new BusinessClass();
		businessClass.sayGreetings();
		
		
		
		InOrder inOrder = inOrder(helloWorldServiceMock);

		// check that askName is called by "first" and "last" parementers in order
		inOrder.verify(helloWorldServiceMock).askName("first");
		inOrder.verify(helloWorldServiceMock).askName("last");
		inOrder.verify(helloWorldServiceMock).sayHi(hiCaptor.capture());
		inOrder.verify(helloWorldServiceMock).sayGoodbye(goodbyeCaptor.capture());
		
		assertEquals("richard feynman",    hiCaptor.getValue());
		assertEquals("richard",    goodbyeCaptor.getValue());
		
		
		verify(helloWorldServiceMock);
		
		
		
    }

}
