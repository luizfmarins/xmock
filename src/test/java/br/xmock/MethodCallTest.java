package br.xmock;

import javassist.util.proxy.ProxyObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class MethodCallTest {

	@Mock private ProxyObject instance;
	@Mock private XMockMethodHandler methodhandler;
	
	@Test
	public void a() {
		Mockito.when(instance.getHandler()).thenReturn(methodhandler);
		
		MethodCall<String> methodCalled = new MethodCall<String>(instance, Person.getMethodGetName());
		methodCalled.thenReturn("luiz");
		
		Mockito.verify(methodhandler).addReturnPromise(Mockito.any(ActualReturnPromise.class));
	}
	
}
