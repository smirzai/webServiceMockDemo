package org.sdco.webservicemock;

import javax.xml.ws.Endpoint;

public class RunAll {
	public static void main(String[] args) {
		System.out.println("Starting Server");
		HelloWorldImpl implementor = new HelloWorldImpl();
		String address = "http://localhost:9000/helloWorld";
		Endpoint.publish(address, implementor);
		BusinessClass businessClass = new BusinessClass();
		businessClass.sayGreetings();
	}
}
