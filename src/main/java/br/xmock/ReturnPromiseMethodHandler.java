package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyObject;

class ReturnPromiseMethodHandler implements MethodHandler {

	private final SpyMethodHandler spyMethodHandler;
	private final ActualReturnPromise returnPromise;
	
	public ReturnPromiseMethodHandler(SpyMethodHandler spyMethodHandler, ActualReturnPromise returnPromise) {
		this.spyMethodHandler = spyMethodHandler;
		this.returnPromise = returnPromise;
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) {
		configureReturnPromisseForSpyMethodHandler(currentMethod, args);
		reconfigureSpyMethodHandlerOnObject(self);
		return null;
	}

	private void configureReturnPromisseForSpyMethodHandler(Method currentMethod, Object[] args) {
		returnPromise.setMethod(currentMethod, args);
		spyMethodHandler.addReturnPromise(returnPromise);
	}

	private void reconfigureSpyMethodHandlerOnObject(Object self) {
		((ProxyObject) self).setHandler(spyMethodHandler);
	}
	
}
