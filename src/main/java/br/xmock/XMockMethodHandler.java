package br.xmock;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javassist.util.proxy.MethodHandler;

abstract class XMockMethodHandler implements MethodHandler {
	
	protected Map<Method, ReturnPromise> methodsReturnPromises = new HashMap<Method, ReturnPromise>();
	
	public void addReturnPromise(ActualReturnPromise returnPromise) {
		methodsReturnPromises.put(returnPromise.getMethod(), returnPromise);
	}
}
