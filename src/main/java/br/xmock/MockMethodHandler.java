package br.xmock;

import java.lang.reflect.Method;
import java.util.Map;

public class MockMethodHandler extends XMockMethodHandler {
	
	private MockMethodHandler() {}
	
	MockMethodHandler(Map<MethodCall, ReturnPromise> methodsReturnPromises, MethodCallFactory methodCallFactory) {
		super(methodsReturnPromises, methodCallFactory);
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) throws Throwable {
		notifyMethodCallFactory(self, currentMethod, args);
		
		return super.invoke(self, currentMethod, proceedMethod, args);
	}

	@Override
	protected ReturnPromise getReturnPromisse(MethodCall method) {
		ReturnPromise promisse = methodsReturnPromises.get(method);
		return promisse != null ? promisse : NoReturnPromise.newInstance();
	}
	
	private void notifyMethodCallFactory(Object self, Method currentMethod, Object[] args) {
		MethodCallMockFactory factory = MethodCallMockFactory.getInstance();
		factory.setLastInstance(self);
		factory.setLastMethodCalled(currentMethod);
		factory.setLastMethodParameters(args);
	}
	
	public static MockMethodHandler newInstance() {
		return new MockMethodHandler();
	}
}
