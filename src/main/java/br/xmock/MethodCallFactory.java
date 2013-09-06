package br.xmock;

import java.lang.reflect.Method;

public class MethodCallFactory {

	private final ParameterFactory parameterFactory; 
	private static final MethodCallFactory instance = new MethodCallFactory();

	MethodCallFactory(ParameterFactory parameterFactory) {
		this.parameterFactory = parameterFactory;
	}
	
	private MethodCallFactory() {
		parameterFactory = ParameterFactory.getInstance();
	}
	
	public MethodCall createForMock(Method method, Object[] params) {
		return new MethodCall(method, parameterFactory.createForMock(params));
	}
	
	public MethodCall createForRealCall(Method method, Object[] params) {
		return new MethodCall(method, parameterFactory.createForRealCall(params));
	}
	
	public static MethodCallFactory getInstance() {
		return instance;
	}
}
