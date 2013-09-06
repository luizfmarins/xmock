package br.xmock;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
	@Mock private Map<MethodCall, ReturnPromise> methodsReturnPromises;
	@Mock private Person person;
	@Mock private MethodCall methodCall;
	@Mock private MethodCallFactory methodCallFactory;
	
	private MockMethodHandler handler;
	
	private static final String MARINS = "Marins";
	
	@Test
	public void invokeSetLastMethodCalledOnMethodCallFactory() throws Throwable {
		Person person = new Person();
		MockMethodHandler handler = MockMethodHandler.newInstance();
		handler.invoke(person, Person.getMethodGetName(), null, new Object[] {});
		
		assertEquals(Person.getMethodGetName(), MethodCallMockFactory.getInstance().getLastMethodCalled());
	}
	
	@Test
	public void invokeSetLastInstanceOnMethodCallFactory() throws Throwable {
		Person person = new Person();
		MockMethodHandler handler = MockMethodHandler.newInstance();
		handler.invoke(person, Person.getMethodGetName(), null, new Object[] {});
		
		assertEquals(person, MethodCallMockFactory.getInstance().getLastInstance());
	}
	
	@Test
	public void invokeSetLastMethodParametersOnMethodCallFactory() throws Throwable {
		final Object[] parameters = new Object[] {5};
		
		MockMethodHandler handler = MockMethodHandler.newInstance();
		handler.invoke(new Person(), Person.getMethodCalculateAgeInYear(), null, parameters);
		
		assertArrayEquals(parameters , MethodCallMockFactory.getInstance().getLastMethodParameters());
	}
	
	@Test
	public void invoke() throws Throwable {
		Object result = handler.invoke(person, Person.getMethodGetName(), null, new Object[0]);
		
		Assert.assertEquals(MARINS, result);
	}
	
	@Before
	public void setup() {
		Mockito.when(person.getName()).thenReturn(MARINS);
		Mockito.when(returnPromise.getMethodCall()).thenReturn(methodCall);
		Mockito.when(methodsReturnPromises.get(methodCall)).thenReturn(returnPromise);
		Mockito.when(returnPromise.getReturn(Person.getMethodGetName(), new Object[0])).thenReturn(MARINS);
		Mockito.when(methodCallFactory.createForRealCall(Person.getMethodGetName(), new Object[0])).thenReturn(methodCall);
		
		handler = new MockMethodHandler(methodsReturnPromises, methodCallFactory);
	}
}
