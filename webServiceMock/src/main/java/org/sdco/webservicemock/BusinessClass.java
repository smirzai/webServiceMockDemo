package org.sdco.webservicemock;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


// This class is going to call Hello World services
public class BusinessClass {
	
	public   void sayGreetings() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		
		factory.setServiceClass(HelloWorld.class);
		
		factory.setAddress(TestGreeting.helloWorldUrl);
		HelloWorld client = (HelloWorld) factory.create();
		
		
		// ask first name
		
		String firstName = client.askName("first");
		String lastName = client.askName("last");
		 
		client.sayHi( firstName + " " + lastName);
		
		// we are freinds now, use only first name
		client.sayGoodbye( firstName);
	
		
		
	}

}
