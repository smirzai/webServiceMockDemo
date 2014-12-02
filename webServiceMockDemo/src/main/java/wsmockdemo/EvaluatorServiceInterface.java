package wsmockdemo;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "urn:net:atos:demo")
public interface EvaluatorServiceInterface {
	@WebMethod
	@WebResult(name = "output", targetNamespace = "urn:net:atos:demo")
	boolean evaluate(String expr);

}