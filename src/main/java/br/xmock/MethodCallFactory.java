package br.xmock;

import java.lang.reflect.Method;

class MethodCallFactory {

	private static Method lastMethodCalled;
	private static Object lastInstance;
	
	public static Method getLastMethodCalled() {
		return lastMethodCalled;
	}
	
	public static void setLastMethodCalled(Method method) {
		lastMethodCalled = method;
	}
	
	public static <T> MethodCall<T> newInstance(T obj) {
		return new MethodCall<T>(lastInstance, lastMethodCalled);
	}

	public static Object getLastInstance() {
		return lastInstance;
	}
	
	public static void setLastInstance(Object instance) {
		lastInstance = instance;
	}
}
