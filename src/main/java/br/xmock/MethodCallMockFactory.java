package br.xmock;

import java.lang.reflect.Method;

class MethodCallMockFactory {
	
	private Object lastInstance;
	private Method lastMethodCalled;
	private Object[] lastMethodParameters;
	
	private static final MethodCallMockFactory instance = new MethodCallMockFactory();  
	
	private MethodCallMockFactory() {}
	
	public <T> MethodCallMock<T> create(T obj) {
		return new MethodCallMock<T>(lastInstance, lastMethodCalled, lastMethodParameters);
	}
	
	public void setLastMethodCalled(Method method) {
		lastMethodCalled = method;
	}
	
	public void setLastInstance(Object instance) {
		lastInstance = instance;
	}
	
	public void setLastMethodParameters(Object[] parameters) {
		this.lastMethodParameters = parameters;
	}

	Object getLastInstance() {
		return lastInstance;
	}
	
	Method getLastMethodCalled() {
		return lastMethodCalled;
	}
	
	Object[] getLastMethodParameters() {
		return lastMethodParameters;
	}
	
	public static MethodCallMockFactory getInstance() {
		return instance;
	}
}
