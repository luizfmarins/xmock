package br.xmock;

import javassist.util.proxy.ProxyObject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class VerifyMethodHandlerTest {
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Mock private XMockMethodHandler xmockMethodHandler;
	@Mock private ProxyObject person;
	
	private VerifyMethodHandler methodHandler;

	@Test
	public void invokeAskToXMockMethodHandlerIfTheMethodWasCalled() throws Throwable {
		methodHandler.invoke(person, Person.getMethodGetName(), null, new Object[0]);
		
		Mockito.verify(xmockMethodHandler).getTimesCalled(Person.getMethodGetName(), new Object[0]);
	}
	
	@Test
	public void invokeThrowsExceptionItTimesCalledIsDifferent() throws Throwable {
		Mockito.when(xmockMethodHandler.getTimesCalled(Person.getMethodGetName(), new Object[0])).thenReturn(0);
		
		exception.expect(VerifyMethodCallException.class);
		exception.expectMessage("Wanted to be invoked 1 time(s): public java.lang.String br.xmock.fake.classes.Person.getName()");
		
		methodHandler.invoke(person, Person.getMethodGetName(), null, new Object[0]);
	}
	
	@Before
	public void setup() {
		Mockito.when(xmockMethodHandler.getTimesCalled(Person.getMethodGetName(), new Object[0])).thenReturn(1);
		methodHandler = new VerifyMethodHandler(xmockMethodHandler, 1);
	}
}
