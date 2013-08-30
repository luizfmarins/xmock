package br.xmock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.xmock.fake.classes.Person;

public class MockMethodHandlerTest {

	@Test
	public void invokeSetLastMethodCalledOnMethodCallFactory() throws Throwable {
		Person person = new Person();
		MockMethodHandler handler = MockMethodHandler.newInstance();
		handler.invoke(person, Person.getMethodGetName(), null, new Object[] {});
		
		assertEquals(Person.getMethodGetName(), MethodCallFactory.getLastMethodCalled());
	}
	
	@Test
	public void invokeSetLastInstanceMethodCallFactory() throws Throwable {
		Person person = new Person();
		MockMethodHandler handler = MockMethodHandler.newInstance();
		handler.invoke(person, Person.getMethodGetName(), null, new Object[] {});
		
		assertEquals(person, MethodCallFactory.getLastInstance());
	}
}
