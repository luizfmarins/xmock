package br.xmock;

import java.lang.reflect.Method;

class NoReturnPromisse extends ReturnPromise {

	private NoReturnPromisse() {}
	
	@Override
	protected Object getReturn(Method method, Object[] params) throws Throwable {
		return null;
	}
	
	public static NoReturnPromisse newInstance() {
		return new NoReturnPromisse();
	}
}
