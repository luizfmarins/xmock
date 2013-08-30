package br.xmock;

import java.lang.reflect.Method;

public abstract class ReturnPromise {

	public <T> T when(T mockedObject) {
		return mockedObject;
	}
	
	protected abstract Object getReturn(Method method, Object[] params) throws Throwable;
}
