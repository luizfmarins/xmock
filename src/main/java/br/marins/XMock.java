package br.marins;

import javassist.util.proxy.ProxyFactory;

public class XMock {

	public static <T> T spy(T object) {
		return createSpiedObject(object);
	}

	public static ActualReturnPromise doReturn(Object promiseOfReturn) {
		return new ActualReturnPromise(promiseOfReturn);
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T createSpiedObject(T object) {
		try {
			ProxyFactory factory = new ProxyFactory();
			factory.setSuperclass(object.getClass());
			
			SpyMethodHandler methodhandler = new SpyMethodHandler();
			factory.setFilter(new SpyMethodFilter());
			
			T spiedObject = (T) factory.create(new Class[0], new Object[0], methodhandler);
			
			methodhandler.setRealInstance(object);
			
			return spiedObject;
		} catch (Exception ex) {
			throw new RuntimeException("Unable to spy " + object);
		}
	}
}
