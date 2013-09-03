package br.xmock;

import java.lang.reflect.Method;
import java.util.Map;

public class MockMethodHandler extends XMockMethodHandler {
	
	private MockMethodHandler() {}
	
	MockMethodHandler(Map<Method, ReturnPromise> methodsReturnPromises) {
		super(methodsReturnPromises);
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) throws Throwable {
		MethodCallFactory.getInstance().setLastInstance(self);
		MethodCallFactory.getInstance().setLastMethodCalled(currentMethod);
		
		return super.invoke(self, currentMethod, proceedMethod, args);
	}

	@Override
	protected ReturnPromise getReturnPromisse(Method method) {
		ReturnPromise promisse = methodsReturnPromises.get(method);
		return promisse != null ? promisse : NoReturnPromisse.newInstance();
	}
	
	public static MockMethodHandler newInstance() {
		return new MockMethodHandler();
	}
}
