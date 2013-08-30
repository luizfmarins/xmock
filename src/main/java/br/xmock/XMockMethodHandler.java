package br.xmock;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javassist.util.proxy.MethodHandler;

abstract class XMockMethodHandler implements MethodHandler {
	
	protected Map<Method, ReturnPromise> methodsReturnPromises = new HashMap<Method, ReturnPromise>();
	
	protected XMockMethodHandler() {}
	
	protected XMockMethodHandler(Map<Method, ReturnPromise> methodsReturnPromises) {
		this.methodsReturnPromises = methodsReturnPromises;
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) throws Throwable {
		return getReturnPromisse(currentMethod).getReturn(currentMethod, args);
	}
	
	public void addReturnPromise(ActualReturnPromise returnPromise) {
		methodsReturnPromises.put(returnPromise.getMethod(), returnPromise);
	}

	protected abstract ReturnPromise getReturnPromisse(Method method);
}
