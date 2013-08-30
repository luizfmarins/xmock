package br.xmock;

import java.lang.reflect.Method;
import java.util.Map;

class SpyMethodHandler extends XMockMethodHandler {

	private Object realInstance;
	
	private SpyMethodHandler() {}
	
	SpyMethodHandler(Map<Method, ReturnPromise> methodsReturnPromises) {
		this.methodsReturnPromises = methodsReturnPromises;
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) throws Throwable {
		return getReturnPromisse(currentMethod).getReturn(currentMethod, args);
	}

	public ReturnPromise getReturnPromisse(Method method) {
		ReturnPromise promise = methodsReturnPromises.get(method);
		return promise != null ? promise : new NoReturnPromise(realInstance);
	}
	
	public void setRealInstance(Object realInstance) {
		this.realInstance = realInstance;
	}

	public static SpyMethodHandler newInstance() {
		return new SpyMethodHandler();
	}
}
