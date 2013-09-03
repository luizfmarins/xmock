package br.xmock;

import javassist.util.proxy.ProxyObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class ReturnPromiseMethodHandlerTest {

	@Mock private SpyMethodHandler spyMethodHandler;
	@Mock private ActualReturnPromise returnPromise;
	
	private ReturnPromiseMethodHandler handler;
	
	@Test
	public void invoke() {
		ProxyObject person = Mockito.mock(ProxyObject.class);
		
		handler.invoke(person, Person.getMethodGetName(),null, new Object[0]);
		
		Mockito.verify(spyMethodHandler).addReturnPromise(returnPromise);
	}
	
	@Test
	public void invokeSetTheMethodInTheReturnPromisse() {
		ProxyObject person = Mockito.mock(ProxyObject.class);
		
		handler.invoke(person, Person.getMethodGetName(),null, new Object[0]);
		
		Mockito.verify(returnPromise).setMethod(Person.getMethodGetName(), new Object[0]);
	}
	
	@Before
	public void setup() {
		handler = new ReturnPromiseMethodHandler(spyMethodHandler, returnPromise);
	}
}
