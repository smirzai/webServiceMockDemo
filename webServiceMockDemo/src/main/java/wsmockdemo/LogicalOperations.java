package wsmockdemo;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * This is the class we want to test, it relies on a webservice.
 */
public class LogicalOperations {
	private EvaluatorServiceInterface evaluator;


	// this web class is depending on external web services for evaluating given string.
	// in reality this would be injected instead of being looked up locally
	public LogicalOperations() throws MalformedURLException {
		URL wsdlURL = new URL("http://localhost:8081/ws?wsdl");
		QName SERVICE_NAME = new QName("http://www.atos.net", "EvaluatorService");
		Service service = Service.create(wsdlURL, SERVICE_NAME);
		evaluator = service.getPort(EvaluatorServiceInterface.class);
	}

	
	public boolean and(String a, String b) {
		if (!evaluator.evaluate(a))
			return false;
		return evaluator.evaluate(b);
	}

}
