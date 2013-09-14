package br.xmock;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public class XMock {
	
	public static <T> T spy(T object) {
		return createSpiedObject(object);
	}

	public static <T> T mock(Class<T> classToMock) {
		return createMock(classToMock);
	}
	
	public static ReturnPromiseCreator doReturn(Object promiseOfReturn) {
		return ReturnPromiseCreator.newInstance(promiseOfReturn);
	}
	
	public static <T> MethodCallMock<T> when(T object) {
		return MethodCallMockFactory.getInstance().create(object);
	}
	
	public static <T> T verify(T object) {
		return verify(object, 1);
	}
	
	public static <T> T verify(T object, int times) {
		ProxyObject proxy = (ProxyObject) object;
		XMockMethodHandler oldHandler = (XMockMethodHandler) proxy.getHandler();
		
		proxy.setHandler(new VerifyMethodHandler(oldHandler, times));
		
		return object;
	}
	
	public static Integer anyInt() {
		ParameterFactory.getInstance().setUseAnyObject();
		return 0;
	}
	
	public static int times(int i) {
		return i;
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