package br.marins;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.marins.fake.classes.NaturalPerson;
import br.marins.fake.classes.Person;

public class XMockTest {

	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void spyHappyDay() {
		Person person = XMock.spy(new Person());
		XMock.doReturn("Marins").when(person).getName();
		
		assertEquals("Marins", person.getName());
	}
	
	@Test
	public void invokeMethodWithNoReturnPromise() {
		Person spiedPerson = XMock.spy(new NaturalPerson());

		String name = spiedPerson.getName();
		
		assertEquals("Luiz", name);
	}
	
	@Test
	public void spyNull() {
		exception.expect(RuntimeException.class);
		exception.expectMessage("Unable to spy null");
		XMock.spy(null);
	}
}
