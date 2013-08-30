package br.xmock;

import java.lang.reflect.Method;
import java.util.Map;

class SpyMethodHandler extends XMockMethodHandler {

	private Object realInstance;
	
	private SpyMethodHandler() {}
	
	SpyMethodHandler(Map<Method, ReturnPromise> methodsReturnPromises) {
		super(methodsReturnPromises);
	}
	
	@Override
	protected ReturnPromise getReturnPromisse(Method method) {
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
