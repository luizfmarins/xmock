package br.xmock;

import java.lang.reflect.Method;

public class MethodCall<T> {

	private final Object instance;
	private final Method methodCalled;
	
	MethodCall(Object instance, Method methodCalled) {
		this.instance = instance;
		this.methodCalled = methodCalled;
	}
	
	public void thenReturn(T value) {
		// TODO Auto-generated method stub
	}

	Object getInstance() {
		return instance;
	}
	
	Method getMethod() {
		return methodCalled;
	}
}
