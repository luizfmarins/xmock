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

import br.xmock.DelegateToRealInstanceMethodCall;
import br.xmock.ReturnPromise;
import br.xmock.SpyMethodHandler;
import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class SpyMethodHandlerTest {

	@Mock private ActualReturnPromise returnPromise;
	@Mock private Map<MethodCall, ReturnPromise> methodsReturnPromises;
	@Mock private Person person;
	@Mock private MethodCall methodCall;
	@Mock private MethodCallFactory methodCallFactory;

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
		
		Mockito.verify(methodsReturnPromises).get(methodCall);
	}
	
	@Test
	public void addReturnPromise() {
		handler.addReturnPromise(returnPromise);
		
		Mockito.verify(methodsReturnPromises).put(methodCall, returnPromise);
	}
	
	@Test
	public void getReturnPromise() {
		ReturnPromise actual = handler.getReturnPromisse(methodCall);
		
		assertEquals(returnPromise, actual);
	}
	
	@Test
	public void getReturnPromiseForMethodWithNoPromise() {
		ReturnPromise promise = handler.getReturnPromisse(new MethodCall(Person.getMethodGetAge(), ParameterFactory.getInstance().createForRealCall(new Object[0])));
		
		assertTrue(promise instanceof DelegateToRealInstanceMethodCall);
	}
	
	@Before
	public void setup() {
		Mockito.when(person.getName()).thenReturn(MARINS);
		Mockito.when(returnPromise.getMethodCall()).thenReturn(methodCall);
		Mockito.when(methodsReturnPromises.get(methodCall)).thenReturn(returnPromise);
		Mockito.when(returnPromise.getReturn(Person.getMethodGetName(), new Object[0])).thenReturn(MARINS);
		Mockito.when(methodCallFactory.createForRealCall(Person.getMethodGetName(), new Object[0])).thenReturn(methodCall);
		
		handler = new SpyMethodHandler(methodsReturnPromises, methodCallFactory);
	}
}
