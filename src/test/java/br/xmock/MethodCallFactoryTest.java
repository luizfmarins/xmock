package br.xmock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import br.xmock.fake.classes.Person;

public class MethodCallFactoryTest {

	@Test
	public void newInstance() {
		Person person = Mockito.mock(Person.class);
		MethodCallFactory.setLastInstance(person);
		MethodCallFactory.setLastMethodCalled(Person.getMethodGetName());
		
		MethodCall<String> methodCall = MethodCallFactory.newInstance(person.getName());
		
		assertEquals(person, methodCall.getInstance());
		assertEquals(Person.getMethodGetName(), methodCall.getMethod());
	}
	
}
