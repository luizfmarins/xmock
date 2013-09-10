package br.xmock;

import java.lang.reflect.Method;

public abstract class ReturnPromise {

	protected abstract Object getReturn(Method method, Object[] params) throws Throwable;
}
