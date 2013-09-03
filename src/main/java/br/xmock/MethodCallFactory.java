package br.xmock;

import java.lang.reflect.Method;

public class MethodCallFactory {

	private static final MethodCallFactory instance = new MethodCallFactory();
	
	public MethodCall create(Method method, Object[] params) {
		return new MethodCall(method, params);
	}
	
	public static MethodCallFactory getInstance() {
		return instance;
	}
}
