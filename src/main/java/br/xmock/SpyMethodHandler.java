package br.xmock;

import java.util.Map;

class SpyMethodHandler extends XMockMethodHandler {

	private Object realInstance;
	
	private SpyMethodHandler() {}
	
	SpyMethodHandler(Map<MethodCall, ReturnPromise> methodsReturnPromises, MethodCallFactory methodCallFactory) {
		super(methodsReturnPromises, methodCallFactory);
	}
	
	@Override
	protected ReturnPromise getReturnPromisse(MethodCall method) {
		ReturnPromise promise = methodsReturnPromises.get(method);
		return promise != null ? promise : new DelegateToRealInstanceMethodCall(realInstance);
	}
	
	public void setRealInstance(Object realInstance) {
		this.realInstance = realInstance;
	}

	public static SpyMethodHandler newInstance() {
		return new SpyMethodHandler();
	}
}
