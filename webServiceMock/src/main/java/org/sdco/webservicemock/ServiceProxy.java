package org.sdco.webservicemock;



import java.lang.reflect.Method;


public class ServiceProxy implements java.lang.reflect.InvocationHandler {
	private Object obj;
	
	public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(
            obj.getClass().getClassLoader(),
            obj.getClass().getInterfaces(),
            new ServiceProxy(obj));
    }
	
	
	 private ServiceProxy(Object obj) {
	        this.obj = obj;
	    }
	 
	 @Override
	 public Object invoke(Object proxy, Method m, Object[] args)
		        throws Throwable {
		 Object result;
	        try {
	        	
	            result = m.invoke(obj, args);
	        } catch (Exception e) {
	            throw new RuntimeException("unexpected invocation exception: " +
	                                       e.getMessage());
	        } 
	        
	        return result;
	}	 
}
