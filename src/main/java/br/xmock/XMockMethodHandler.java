package br.xmock;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


import javassist.util.proxy.MethodHandler;

abstract class XMockMethodHandler implements MethodHandler {
	
	protected final Map<MethodCall, ReturnPromise> methodsReturnPromises;
	private final MethodCallFactory methodCallFactory;
	private final Map<MethodCall, Integer> timesCalled = new HashMap<MethodCall, Integer>();
	
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
		
		registerMethodCall(methodCall);
		
		return getReturnPromisse(methodCall).getReturn(currentMethod, args);
	}

	public void addReturnPromise(ActualReturnPromise returnPromise) {
		methodsReturnPromises.put(returnPromise.getMethodCall(), returnPromise);
	}
	
	public int getTimesCalled(Method method, Object[] args) {
		MethodCall methodCall = methodCallFactory.createForRealCall(method, args);
		Integer times = timesCalled.get(methodCall);
		return times != null ? times : 0;
	}
	
	protected abstract ReturnPromise getReturnPromisse(MethodCall method);

	private void registerMethodCall(MethodCall methodCall) {
		if (!timesCalled.containsKey(methodCall))
			timesCalled.put(methodCall, 0);
		
		Integer oldTimes = timesCalled.get(methodCall);
		timesCalled.put(methodCall, oldTimes + 1);
	}
}