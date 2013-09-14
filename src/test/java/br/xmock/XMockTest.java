package br.xmock;

import static org.junit.Assert.assertTrue;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyObject;

import org.junit.Test;

import br.xmock.fake.classes.Person;

public class XMockTest {

	@Test
	public void verifyChangesMethodHandlerToVerifyMethodHanlder() {
		Person person = XMock.mock(Person.class);
		
		XMock.verify(person);
		
		MethodHandler handler = ((ProxyObject)person).getHandler();
		
		assertTrue(handler instanceof VerifyMethodHandler);
	}
	
}
