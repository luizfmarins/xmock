package br.xmock;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

public class XMock {
	
	public static <T> T spy(T object) {
		return createSpiedObject(object);
	}

	public static <T> T mock(Class<T> classToMock) {
		return createMock(classToMock);
	}
	
	public static ReturnPromise doReturn(Object promiseOfReturn) {
		return ActualReturnPromise.newInstance(promiseOfReturn);
	}
	
	public static <T> MethodCall<T> when(T object) {
		return MethodCallFactory.newInstance(object);
	}
	
	private static <T> T createSpiedObject(T object) {
		try {
			ProxyFactory factory = new ProxyFactory();
			factory.setSuperclass(object.getClass());
			
			SpyMethodHandler methodHandler = SpyMethodHandler.newInstance();
			factory.setFilter(new SpyMethodFilter());
			
			@SuppressWarnings("unchecked")
			T spiedObject = (T) factory.create(new Class[0], new Object[0], methodHandler);
			
			methodHandler.setRealInstance(object);
			
			return spiedObject;
		} catch (Exception ex) {
			throw new RuntimeException("Unable to spy " + object);
		}
	}
	
	private static <T> T createMock(Class<T> classToMock) {
		try {
			ProxyFactory factory = new ProxyFactory();
			factory.setSuperclass(classToMock);
			
			MethodHandler methodHandler = MockMethodHandler.newInstance();
			
			@SuppressWarnings("unchecked")
			T spiedObject = (T) factory.create(new Class[0], new Object[0], methodHandler);
			
			return spiedObject;
		} catch (Exception ex) {
			throw new RuntimeException("Unable to mock class" + classToMock, ex);
		}
	}
}
