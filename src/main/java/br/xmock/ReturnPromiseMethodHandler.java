package br.xmock;

import java.lang.reflect.Method;


import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyObject;

class ReturnPromiseMethodHandler implements MethodHandler {

	private final SpyMethodHandler spyMethodHandler;
	private final ReturnPromiseCreator returnPromiseCreator;
	
	public ReturnPromiseMethodHandler(SpyMethodHandler spyMethodHandler, ReturnPromiseCreator returnPromisseCreator) {
		this.spyMethodHandler = spyMethodHandler;
		this.returnPromiseCreator = returnPromisseCreator;
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) {
		configureReturnPromisseForSpyMethodHandler(currentMethod, args);
		reconfigureSpyMethodHandlerOnObject(self);
		return null;
	}

	private void configureReturnPromisseForSpyMethodHandler(Method currentMethod, Object[] args) {
		ActualReturnPromise returnPromise = returnPromiseCreator.create(currentMethod, args);
		spyMethodHandler.addReturnPromise(returnPromise);
	}

	private void reconfigureSpyMethodHandlerOnObject(Object self) {
		((ProxyObject) self).setHandler(spyMethodHandler);
	}
	
}
