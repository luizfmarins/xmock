package br.xmock;

import javassist.util.proxy.ProxyObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class MethodCallMockTest {

	@Mock private ProxyObject instance;
	@Mock private XMockMethodHandler methodhandler;
	
	@Test
	public void thenReturnAddAActualReturnPromisseToTheMockMethodHandler() {
		Mockito.when(instance.getHandler()).thenReturn(methodhandler);
		
		MethodCallMock<String> methodCalled = new MethodCallMock<String>(instance, Person.getMethodGetName(), new Object[0]);
		methodCalled.thenReturn("luiz");
		
		Mockito.verify(methodhandler).addReturnPromise(Mockito.any(ActualReturnPromise.class));
	}
	
}
