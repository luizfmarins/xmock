package br.xmock;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


import javassist.util.proxy.MethodHandler;

abstract class XMockMethodHandler implements MethodHandler {
	
	protected final Map<MethodCall, ReturnPromise> methodsReturnPromises;
	private final MethodCallFactory methodCallFactory;
	
	protected XMockMethodHandler() {
		methodsReturnPromises = new HashMap<MethodCall, ReturnPromise>();
		methodCallFactory = MethodCallFactory.getInstance();
	}
	
	protected XMockMethodHandler(Map<MethodCall, ReturnPromise> methodsReturnPromises, MethodCallFactory methodCallFactory) {
		this.methodsReturnPromises = methodsReturnPromises;
		this.methodCallFactory = methodCallFactory;
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) throws Throwable {
		MethodCall methodCall = methodCallFactory.createForRealCall(currentMethod, args);
		return getReturnPromisse(methodCall).getReturn(currentMethod, args);
	}
	
	public void addReturnPromise(ActualReturnPromise returnPromise) {
		methodsReturnPromises.put(returnPromise.getMethodCall(), returnPromise);
	}

	protected abstract ReturnPromise getReturnPromisse(MethodCall method);
}
