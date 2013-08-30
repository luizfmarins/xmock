package br.xmock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.ActualReturnPromise;
import br.xmock.DelegateToRealInstanceMethodCall;
import br.xmock.ReturnPromise;
import br.xmock.SpyMethodHandler;
import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class SpyMethodHandlerTest {

	@Mock private ActualReturnPromise returnPromise;
	@Mock private Map<Method, ReturnPromise> methodsReturnPromises;
	@Mock private Person person;

	private Method getNameMethod = Person.getMethodGetName();
	private SpyMethodHandler handler;
	
	private static final String MARINS = "Marins";
	
	@Test
	public void invoke() throws Throwable {
		Object result = handler.invoke(person, getNameMethod, null, new Object[0]);
		
		Assert.assertEquals(MARINS, result);
	}

	@Test
	public void resultOfInvokedIsDeterminedByTheReturnPromise() throws Throwable {
		handler.invoke(person, getNameMethod, null, new Object[0]);
		
		Mockito.verify(returnPromise).getReturn(getNameMethod, new Object[0]);
	}
	
	@Test
	public void invokeSearchesForTheReturnInTheMap() throws Throwable {
		handler.invoke(person, getNameMethod, null, new Object[0]);
		
		Mockito.verify(methodsReturnPromises).get(Person.getMethodGetName());
	}
	
	@Test
	public void addReturnPromise() {
		handler.addReturnPromise(returnPromise);
		
		Mockito.verify(methodsReturnPromises).put(Person.getMethodGetName(), returnPromise);
	}
	
	@Test
	public void getReturnPromise() {
		ReturnPromise actual = handler.getReturnPromisse(Person.getMethodGetName());
		
		assertEquals(returnPromise, actual);
	}
	
	@Test
	public void getReturnPromiseForMethodWithNoPromise() {
		ReturnPromise promise = handler.getReturnPromisse(Person.getMethodGetAge());
		
		assertTrue(promise instanceof DelegateToRealInstanceMethodCall);
	}
	
	@Before
	public void setup() {
		Mockito.when(person.getName()).thenReturn(MARINS);
		Mockito.when(returnPromise.getMethod()).thenReturn(Person.getMethodGetName());
		Mockito.when(methodsReturnPromises.get(Person.getMethodGetName())).thenReturn(returnPromise);
		Mockito.when(returnPromise.getReturn(Person.getMethodGetName(), new Object[0])).thenReturn(MARINS);
		
		handler = new SpyMethodHandler(methodsReturnPromises);
	}
}
