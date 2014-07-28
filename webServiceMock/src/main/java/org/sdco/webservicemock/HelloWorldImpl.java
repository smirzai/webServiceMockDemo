package org.sdco.webservicemock;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public void sayHi(String text) {
		System.out.println("************************************************");
		System.out.println("hello " + text);
		
	}

	@Override
	public String askName(String part) {
		if (part.equals("first"))
			return "richard";
		if (part.equals("last"))
			return "fineman";
		throw new RuntimeException("not valid name part");
	}

	@Override
	public void sayGoodbye(String text) {
		System.out.println("goodbye " + text);
		
	}

}
