package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.ProxyObject;

public class ReturnPromiseCreator {

	private final Object returnPromisse; 
	
	private ReturnPromiseCreator(Object returnPromisse) {
		this.returnPromisse = returnPromisse;
	}

	public <T> T when(T object) {
		configureReturnPromiseMethodHandler(object);
		return object;
	}
	
	ActualReturnPromise create(Method currentMethod, Object[] args) {
		MethodCall methodCall = MethodCallFactory.getInstance().createForMock(currentMethod, args);
		return ActualReturnPromise.newInstance(methodCall, returnPromisse);
	}
	
	ReturnPromiseMethodHandler newReturnPromiseMethodHandler(SpyMethodHandler spyMethodHandler, ReturnPromiseCreator creator) {
		return new ReturnPromiseMethodHandler(spyMethodHandler, this);
	}
	
	private <T> void configureReturnPromiseMethodHandler(T mockedObject) {
		ProxyObject proxyObject = (ProxyObject) mockedObject;
		
		SpyMethodHandler spyMethodHandler = (SpyMethodHandler) proxyObject.getHandler();
		proxyObject.setHandler(newReturnPromiseMethodHandler(spyMethodHandler, this));
	}
	
	public static ReturnPromiseCreator newInstance(Object returnPromisse) {
		return new ReturnPromiseCreator(returnPromisse);
	}
}
