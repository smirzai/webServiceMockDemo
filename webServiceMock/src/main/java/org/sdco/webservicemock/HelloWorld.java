package org.sdco.webservicemock;

import javax.jws.WebService;


@WebService
public interface HelloWorld {
	
	// Say the text after hi
	void sayHi(String text);
	
	// if part == "first", firstname, if "last" return lastname, Exception otherwise
	String askName(String part);
	
	
	// say the text after goodbye
	void sayGoodbye(String text);

}
