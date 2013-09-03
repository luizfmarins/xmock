package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.ProxyObject;

class ActualReturnPromise extends ReturnPromise {

	private final Object promiseOfReturn;
	private MethodCall methodCall;
	
	private ActualReturnPromise(Object promiseOfReturn) {
		this.promiseOfReturn = promiseOfReturn;
	}
	
	private ActualReturnPromise(Method method, Object promiseOfReturn, Object[] params) {
		this.promiseOfReturn = promiseOfReturn;
		this.methodCall = MethodCallFactory.getInstance().create(method, params);
	}
	
	private ActualReturnPromise(MethodCall methodCall, Object promiseOfReturn) {
		this.promiseOfReturn = promiseOfReturn;
		this.methodCall = methodCall;
	}

	@Override
	public <T> T when(T mockedObject) {
		configureReturnPromiseMethodHandler(mockedObject);
		return mockedObject;
	}

	public MethodCall getMethodCall() {
		return methodCall;
	}
	
	public void setMethod(Method method, Object[] params) {
		this.methodCall = MethodCallFactory.getInstance().create(method, params);
	}

	@Override
	public Object getReturn(Method method, Object[] params) {
		return promiseOfReturn;
	}

	@Deprecated
	Method getMethod() {
		return methodCall.getMethod();
	}
	
	private <T> void configureReturnPromiseMethodHandler(T mockedObject) {
		ProxyObject proxyObject = (ProxyObject) mockedObject;
		
		SpyMethodHandler spyMethodHandler = (SpyMethodHandler) proxyObject.getHandler();
		proxyObject.setHandler(new ReturnPromiseMethodHandler(spyMethodHandler, this));
	}
	
	public static ActualReturnPromise newInstance(Object promiseOfReturn) {
		return new ActualReturnPromise(promiseOfReturn);
	}
	
	public static ActualReturnPromise newInstance(MethodCall methodCall, Object promiseOfReturn) {
		return new ActualReturnPromise(methodCall, promiseOfReturn);
	}
}
