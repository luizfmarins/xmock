package br.xmock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class MethodCallMockFactoryTest {

	@Mock private Person person;
	
	private MethodCallMockFactory factory = MethodCallMockFactory.getInstance();
	
	@Test
	public void create() {
		factory.setLastInstance(person);
		factory.setLastMethodCalled(Person.getMethodGetName());
		factory.setLastMethodParameters(new Object[] {});
		
		MethodCallMock<String> methodCall = factory.create(person.getName());
		
		assertEquals(person, methodCall.getInstance());
		assertEquals(Person.getMethodGetName(), methodCall.getMethod());
	}
}
