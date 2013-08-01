package br.marins;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.marins.fake.classes.Person;

public class XMockTest {

	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void integration() {
		Person person = XMock.spy(new Person());
		XMock.doReturn("Marins").when(person).getName();
		
		assertEquals("Marins", person.getName());
	}
	
	@Test
	public void spyNull() {
		exception.expect(RuntimeException.class);
		exception.expectMessage("Unable to spy null");
		XMock.spy(null);
	}
}
