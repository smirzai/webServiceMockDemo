package wsmockdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Utility class to wrap the webservice implementation in a mock.
 */
public class ServiceProxy implements InvocationHandler {
	private Object obj;

	public static <T> T newInstance(T obj) {
		ServiceProxy proxy = new ServiceProxy(obj);
		Class<?> clazz = obj.getClass();
		T res = (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), proxy);
		return res;
	}

	private ServiceProxy(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Exception {
		try {
			return m.invoke(obj, args);
		} catch (Exception e) {
			throw new RuntimeException("unexpected invocation exception: " + e.getMessage(), e);
		}
	}
}