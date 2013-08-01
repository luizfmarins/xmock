package br.marins;

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

import br.marins.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class SpyMethodHandlerTest {

	@Mock private ReturnPromise returnPromise;
	@Mock private Map<Method, ReturnPromise> methodsReturnPromises;
	@Mock private Person person;

	private Method getNameMethod = Person.getMethodGetName();
	private SpyMethodHandler handler;
	
	private static final String MARINS = "Marins";
	@Test
	public void invoke() {
		Object result = handler.invoke(person, getNameMethod, null, new Object[0]);
		
		Assert.assertEquals(MARINS, result);
	}

	@Test
	public void resultOfInvokedIsDeterminedByTheReturnPromise() {
		handler.invoke(person, getNameMethod, null, new Object[0]);
		
		Mockito.verify(returnPromise).getReturn(person);
	}
	
	@Test
	public void invokeSearchesForTheReturnInTheMap() throws Exception {
		handler.invoke(person, getNameMethod, null, new Object[0]);
		
		Mockito.verify(methodsReturnPromises).get(Person.getMethodGetName());
	}
	
	@Test
	public void addReturnPromise() {
		handler.addReturnPromise(returnPromise);
		
		Mockito.verify(methodsReturnPromises).put(Person.getMethodGetName(), returnPromise);
	}
	
	@Test
	public void getReturnPromisse() {
		ReturnPromise actual = handler.getReturnPromisse(Person.getMethodGetName());
		
		assertEquals(returnPromise, actual);
	}
	
	@Before
	public void setup() {
		Mockito.when(person.getName()).thenReturn(MARINS);
		Mockito.when(returnPromise.getMethod()).thenReturn(Person.getMethodGetName());
		Mockito.when(methodsReturnPromises.get(Person.getMethodGetName())).thenReturn(returnPromise);
		Mockito.when(returnPromise.getReturn(person)).thenReturn(MARINS);
		
		handler = new SpyMethodHandler(methodsReturnPromises);
	}
}
