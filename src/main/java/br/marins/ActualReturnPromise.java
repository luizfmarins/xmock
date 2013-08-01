package br.marins;

import java.lang.reflect.Method;

import javassist.util.proxy.ProxyObject;

public class ActualReturnPromise extends ReturnPromise {

	private final Object promiseOfReturn;
	private Method method;
	
	public ActualReturnPromise(Object promiseOfReturn) {
		this.promiseOfReturn = promiseOfReturn;
	}

	public <T> T when(T mockedObject) {
		configureReturnPromiseMethodHandler(mockedObject);
		return mockedObject;
	}

	public Method getMethod() {
		return method;
	}
	
	public void setMethod(Method method) {
		this.method = method;
	}

	@Override
	public Object getReturn(Method method, Object[] params) {
		return promiseOfReturn;
	}
	
	private <T> void configureReturnPromiseMethodHandler(T mockedObject) {
		ProxyObject proxyObject = (ProxyObject) mockedObject;
		
		SpyMethodHandler spyMethodHandler = (SpyMethodHandler) proxyObject.getHandler();
		proxyObject.setHandler(new ReturnPromiseMethodHandler(spyMethodHandler, this));
	}
}
