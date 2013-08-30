package br.xmock;

import java.lang.reflect.Method;

class NoReturnPromise extends ReturnPromise {

	private final Object realInstance;
	
	public NoReturnPromise(Object realInstance) {
		this.realInstance = realInstance;
	}
	
	@Override
	public Object getReturn(Method method, Object[] params) throws Throwable {
		return method.invoke(realInstance, params);
	}
}
