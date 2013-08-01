package br.marins;

import java.lang.reflect.Method;

public abstract class ReturnPromise {

	public abstract Object getReturn(Method method, Object[] params) throws Throwable;
}
