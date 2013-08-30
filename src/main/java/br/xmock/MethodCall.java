package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.ProxyObject;

public class MethodCall<T> {

	private final Object instance;
	private final Method methodCalled;
	
	MethodCall(Object instance, Method methodCalled) {
		this.instance = instance;
		this.methodCalled = methodCalled;
	}
	
	public void thenReturn(T value) {
		ProxyObject proxy = (ProxyObject) instance;
		XMockMethodHandler handler = (XMockMethodHandler) proxy.getHandler();
		
		handler.addReturnPromise(ActualReturnPromise.newInstance(methodCalled, value));
	}

	Object getInstance() {
		return instance;
	}
	
	Method getMethod() {
		return methodCalled;
	}
}
