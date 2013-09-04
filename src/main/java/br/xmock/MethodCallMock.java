package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.ProxyObject;

public class MethodCallMock<T> {

	private final Object instance;
	private MethodCall methodCall;
	
	MethodCallMock(Object instance, Method methodCalled, Object[] params) {
		this.instance = instance;
		this.methodCall = MethodCallFactory.getInstance().create(methodCalled, params);
	}
	
	public void thenReturn(T value) {
		ProxyObject proxy = (ProxyObject) instance;
		XMockMethodHandler handler = (XMockMethodHandler) proxy.getHandler();
		
		handler.addReturnPromise(ActualReturnPromise.newInstance(methodCall, value));
	}

	Object getInstance() {
		return instance;
	}
	
	Method getMethod() {
		return methodCall.getMethod();
	}
}
