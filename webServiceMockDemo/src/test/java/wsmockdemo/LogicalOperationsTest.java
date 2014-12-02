package wsmockdemo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.EndpointImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LogicalOperationsTest {
	
	// this is the mock for evaluator service
	@Mock
	EvaluatorServiceInterface evaluatorServiceMock;

	
	void resetMocks() {
		reset(evaluatorServiceMock);
		
		// define mock behavior
		when(evaluatorServiceMock.evaluate("true")).thenReturn(true);
		when(evaluatorServiceMock.evaluate("false")).thenReturn(false);
		
	}
	
	// test functionality of logical operator and
	@Test
	public void testLogicalOperationsWithMock() throws Exception {
		
		// wrap the evaluator mock in proxy
		EvaluatorServiceInterface serviceInterface = ServiceProxy.newInstance(evaluatorServiceMock);
				
		// publish the mock 
		String url = "http://localhost:8081/ws";
		org.apache.cxf.jaxws.EndpointImpl endpoint = (EndpointImpl) Endpoint.create(serviceInterface);
		
		// we have to use the following CXF dependent code, to specify qname, so that it resource locator finds it 
		QName serviceName = new QName("http://www.atos.net", "EvaluatorService");
		endpoint.setServiceName(serviceName);
		endpoint.publish(url);
		
		
		LogicalOperations logicalOperator = new LogicalOperations();
		try {
			// test if true and true yields true. Make sure that the evaluator function has been called two times with "true" argument
			resetMocks();
			assertTrue(logicalOperator.and("true", "true"));
			verify(evaluatorServiceMock, times(2)).evaluate("true");
			
			
			// test if false and true yields false. Make sure the evaluate is called only once with "false" parameter. The second "true" arguement should not be really checked 
			resetMocks();
			assertFalse(logicalOperator.and("false", "true"));
			verify(evaluatorServiceMock).evaluate("false");
			verify(evaluatorServiceMock, never()).evaluate("true");
			
			
			// test if true and false yields false. Make sure that evaluate function is called twice, one with "true" and other with "false" as argument
			resetMocks();
			assertFalse(logicalOperator.and("true", "false"));
			verify(evaluatorServiceMock).evaluate("false");
			verify(evaluatorServiceMock).evaluate("true");
			
			// test if false and false yields false. Make sure that evaluate function is called only once for the first parameter
			resetMocks();
			assertFalse(logicalOperator.and("false", "false"));
			verify(evaluatorServiceMock, times(1)).evaluate("false");
			
			
			
		} finally {
			endpoint.stop();
		}
		
	}

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
}
