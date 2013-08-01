package br.marins;

import static org.mockito.Mockito.never;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.marins.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class ActualReturnPromiseTest {

	@Mock private Person person;
	
	private ActualReturnPromise returnPromise;
	
	private static final String PROMISE = "promise";

	@Test
	public void getReturnIsTheValueConfigured() {
		Object result = returnPromise.getReturn(Person.getMethodGetName(), new Object[0]);
		
		Assert.assertEquals(PROMISE, result);
	}
	
	@Test
	public void getReturnDoesNotCallTheMethodOnTheRealObject() {
		returnPromise.getReturn(Person.getMethodGetName(), new Object[0]);
		
		Mockito.verify(person, never()).getName();
	}
	
	@Before
	public void setup() {
		Mockito.when(person.getName()).thenReturn("Marins");
		returnPromise = new ActualReturnPromise(PROMISE);
	}
	
	
}
