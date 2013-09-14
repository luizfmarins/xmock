package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;

class VerifyMethodHandler implements MethodHandler {

	private final XMockMethodHandler mockMethodHandler; 
	private final int timesShouldHaveBeenCalled;
	
	VerifyMethodHandler(XMockMethodHandler mockMethodHandler, int timesShouldHaveBeenCalled) {
		this.mockMethodHandler = mockMethodHandler;
		this.timesShouldHaveBeenCalled = timesShouldHaveBeenCalled;
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) throws Throwable {
		int timesCalled = mockMethodHandler.getTimesCalled(currentMethod, args);
		
		if (timesCalled != timesShouldHaveBeenCalled)
			throw new VerifyMethodCallException("Wanted to be invoked " + timesShouldHaveBeenCalled + " time(s): " + currentMethod.toString());
		
		return null;
	}
}
