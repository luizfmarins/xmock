package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.ProxyObject;

class ActualReturnPromise extends ReturnPromise {

	private final Object promiseOfReturn;
	private Method method;
	
	private ActualReturnPromise(Object promiseOfReturn) {
		this.promiseOfReturn = promiseOfReturn;
	}
	
	private ActualReturnPromise(Method method, Object promiseOfReturn) {
		this.promiseOfReturn = promiseOfReturn;
		this.method = method;
	}

	@Override
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
	
	public static ActualReturnPromise newInstance(Object promiseOfReturn) {
		return new ActualReturnPromise(promiseOfReturn);
	}
	
	public static ActualReturnPromise newInstance (Method method, Object promiseOfReturn) {
		return new ActualReturnPromise(method, promiseOfReturn);
	}
}
