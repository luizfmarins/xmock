package br.marins;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyObject;

public class ReturnPromiseMethodHandler implements MethodHandler {

	private final SpyMethodHandler spyMethodHandler;
	private final ReturnPromise returnPromise;
	
	public ReturnPromiseMethodHandler(SpyMethodHandler spyMethodHandler, ReturnPromise returnPromise) {
		this.spyMethodHandler = spyMethodHandler;
		this.returnPromise = returnPromise;
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) {
		configureReturnPromisseForSpyMethodHandler(currentMethod);
		reconfigureSpyMethodHandlerOnObject(self);
		return null;
	}

	private void configureReturnPromisseForSpyMethodHandler(Method currentMethod) {
		returnPromise.setMethod(currentMethod);
		spyMethodHandler.addReturnPromise(returnPromise);
	}

	private void reconfigureSpyMethodHandlerOnObject(Object self) {
		((ProxyObject) self).setHandler(spyMethodHandler);
	}
	
}
