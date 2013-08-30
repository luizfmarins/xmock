package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;

public class MockMethodHandler implements MethodHandler {
	
	private MockMethodHandler() {}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) throws Throwable {
		MethodCallFactory.setLastInstance(self);
		MethodCallFactory.setLastMethodCalled(currentMethod);
		return null;
	}
	
	public static MockMethodHandler newInstance() {
		return new MockMethodHandler();
	}

}
