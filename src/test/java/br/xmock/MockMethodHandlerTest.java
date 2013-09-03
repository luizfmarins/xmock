package br.xmock;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class MockMethodHandlerTest {

	@Mock private ActualReturnPromise returnPromise;
	@Mock private Map<Method, ReturnPromise> methodsReturnPromises;
	@Mock private Person person;
	
	private MockMethodHandler handler;
	
	private static final String MARINS = "Marins";
	
	@Test
	public void invokeSetLastMethodCalledOnMethodCallFactory() throws Throwable {
		Person person = new Person();
		MockMethodHandler handler = MockMethodHandler.newInstance();
		handler.invoke(person, Person.getMethodGetName(), null, new Object[] {});
		
		assertEquals(Person.getMethodGetName(), MethodCallFactory.getInstance().getLastMethodCalled());
	}
	
	@Test
	public void invokeSetLastInstanceMethodCallFactory() throws Throwable {
		Person person = new Person();
		MockMethodHandler handler = MockMethodHandler.newInstance();
		handler.invoke(person, Person.getMethodGetName(), null, new Object[] {});
		
		assertEquals(person, MethodCallFactory.getInstance().getLastInstance());
	}
	
	@Test
	public void invoke() throws Throwable {
		Object result = handler.invoke(person, Person.getMethodGetName(), null, new Object[0]);
		
		Assert.assertEquals(MARINS, result);
	}
	
	@Before
	public void setup() {
		Mockito.when(person.getName()).thenReturn(MARINS);
		Mockito.when(returnPromise.getMethod()).thenReturn(Person.getMethodGetName());
		Mockito.when(methodsReturnPromises.get(Person.getMethodGetName())).thenReturn(returnPromise);
		Mockito.when(returnPromise.getReturn(Person.getMethodGetName(), new Object[0])).thenReturn(MARINS);
		
		handler = new MockMethodHandler(methodsReturnPromises);
	}
}
