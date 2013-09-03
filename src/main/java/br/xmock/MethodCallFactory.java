package br.xmock;

import java.lang.reflect.Method;

class MethodCallFactory {
	
	private Method lastMethodCalled;
	private Object lastInstance;
	
	private static final MethodCallFactory instance = new MethodCallFactory();  
	
	private MethodCallFactory() {}
	
	public <T> MethodCall<T> create(T obj) {
		return new MethodCall<T>(lastInstance, lastMethodCalled);
	}
	
	public Method getLastMethodCalled() {
		return lastMethodCalled;
	}
	
	public void setLastMethodCalled(Method method) {
		lastMethodCalled = method;
	}

	public Object getLastInstance() {
		return lastInstance;
	}
	
	public void setLastInstance(Object instance) {
		lastInstance = instance;
	}
	
	public static MethodCallFactory getInstance() {
		return instance;
	}
}
