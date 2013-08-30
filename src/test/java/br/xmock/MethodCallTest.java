package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.ProxyObject;

import org.junit.Test;
import org.mockito.Mockito;

import br.xmock.fake.classes.Person;

public class MethodCallTest {

	@Test
	public void a() {
		Method method = Person.getMethodGetName();

		ProxyObject instance = Mockito.mock(ProxyObject.class);
		XMockMethodHandler methodhandler = Mockito.mock(XMockMethodHandler.class);
		Mockito.when(instance.getHandler()).thenReturn(methodhandler);
		
		MethodCall<String> methodCalled = new MethodCall<String>(instance, method);
		methodCalled.thenReturn("luiz");
		
		Mockito.verify(methodhandler).addReturnPromise(Mockito.any(ActualReturnPromise.class));
	}
	
}
