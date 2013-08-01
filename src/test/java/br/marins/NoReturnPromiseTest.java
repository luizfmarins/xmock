package br.marins;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.marins.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class NoReturnPromiseTest {

	@Mock private Person person;
	
	@Test
	public void getResultCallsTheMethodInTheRealObject() throws Throwable {
		NoReturnPromise noPromise = new NoReturnPromise(person);
		
		Object result = noPromise.getReturn(Person.getMethodGetName(), new Object[0]);
		
		assertEquals("Marins", result);
		Mockito.verify(person).getName();
	}
	
	@Before
	public void setup() {
		Mockito.when(person.getName()).thenReturn("Marins");
	}
	
}
