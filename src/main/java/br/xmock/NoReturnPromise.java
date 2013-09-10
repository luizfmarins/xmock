package br.xmock;

import java.lang.reflect.Method;

class NoReturnPromise extends ReturnPromise {

	private NoReturnPromise() {}
	
	@Override
	protected Object getReturn(Method method, Object[] params) throws Throwable {
		return null;
	}
	
	public static NoReturnPromise newInstance() {
		return new NoReturnPromise();
	}
}
