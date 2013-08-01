package br.marins;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javassist.util.proxy.MethodHandler;

public class SpyMethodHandler implements MethodHandler {

	private Map<Method, ReturnPromise> methodsReturnPromises = new HashMap<Method, ReturnPromise>();
	
	public SpyMethodHandler() {}
	
	public SpyMethodHandler(Map<Method, ReturnPromise> methodsReturnPromises) {
		this.methodsReturnPromises = methodsReturnPromises;
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) {
		return getReturnPromisse(currentMethod).getReturn(self);
	}

	public void addReturnPromise(ReturnPromise returnPromise) {
		methodsReturnPromises.put(returnPromise.getMethod(), returnPromise);
	}

	public ReturnPromise getReturnPromisse(Method method) {
		return methodsReturnPromises.get(method);
	}

}
