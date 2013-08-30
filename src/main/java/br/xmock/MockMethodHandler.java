package br.xmock;

import java.lang.reflect.Method;

public class MockMethodHandler extends XMockMethodHandler {
	
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
