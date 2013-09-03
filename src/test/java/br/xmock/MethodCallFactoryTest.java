package br.xmock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import br.xmock.fake.classes.Person;

public class MethodCallFactoryTest {

	@Test
	public void newInstance() {
		Person person = Mockito.mock(Person.class);
		MethodCallMockFactory.getInstance().setLastInstance(person);
		MethodCallMockFactory.getInstance().setLastMethodCalled(Person.getMethodGetName());
		
		MethodCallMock<String> methodCall = MethodCallMockFactory.getInstance().create(person.getName());
		
		assertEquals(person, methodCall.getInstance());
		assertEquals(Person.getMethodGetName(), methodCall.getMethod());
	}
	
}
