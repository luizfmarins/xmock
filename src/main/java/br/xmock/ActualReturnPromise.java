package br.xmock;

import java.lang.reflect.Method;

class ActualReturnPromise extends ReturnPromise {

	private final Object promiseOfReturn;
	private final MethodCall methodCall;
	
	private ActualReturnPromise(Method method, Object promiseOfReturn, Object[] params) {
		this.promiseOfReturn = promiseOfReturn;
		this.methodCall = MethodCallFactory.getInstance().createForMock(method, params);
	}
	
	private ActualReturnPromise(MethodCall methodCall, Object promiseOfReturn) {
		this.promiseOfReturn = promiseOfReturn;
		this.methodCall = methodCall;
	}

	public MethodCall getMethodCall() {
		return methodCall;
	}
	
	@Override
	public Object getReturn(Method method, Object[] params) {
		return promiseOfReturn;
	}

	public static ActualReturnPromise newInstance(MethodCall methodCall, Object promiseOfReturn) {
		return new ActualReturnPromise(methodCall, promiseOfReturn);
	}
}
