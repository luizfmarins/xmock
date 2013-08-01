package br.marins;

import javassist.util.proxy.ProxyFactory;

public class XMock {

	private static XMockContext context = XMockContext.getInstance();
	
	public static <T> T spy(T object) {
		return createSpiedObject(object);
	}

	public static ReturnPromise doReturn(Object promiseOfReturn) {
		return new ReturnPromise(promiseOfReturn);
	}
	
	protected static void setXMockContext(XMockContext context) {
		XMock.context = context; 
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T createSpiedObject(T object) {
		try {
			ProxyFactory factory = new ProxyFactory();
			factory.setSuperclass(object.getClass());
			
			SpyMethodHandler methodhandler = new SpyMethodHandler();
			factory.setHandler(methodhandler);
			
			factory.setFilter(new SpyMethodFilter());
			
			T spiedObject = (T) factory.create(new Class[0], new Object[0]);
			methodhandler.setSpiedObject(spiedObject);
			
			context.createContext(spiedObject);
			
			return spiedObject;
		} catch (Exception ex) {
			throw new RuntimeException("Unable to spy " + object);
		}
	}
}
