package br.xmock;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.xmock.fake.classes.NaturalPerson;
import br.xmock.fake.classes.Person;

public class XMockTest {

	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void spyHappyDay() {
		Person person = XMock.spy(new Person());
		XMock.doReturn("Marins").when(person).getName();
		
		assertEquals("Marins", person.getName());
	}
	
	@Test
	public void mockHappyDay() {
		Person person = XMock.mock(Person.class);
		
		XMock.when(person.getName()).thenReturn("Luiz");
		
		assertEquals("Luiz", person.getName());
	}
	
	@Test
	public void invokeMethodOnSpiedObjectWithNoReturnPromise() {
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
	
	@Test
	public void mockMethodWithParameters() {
		Person person = XMock.mock(Person.class);
		
		XMock.when(person.calculateAgeInYear(2013)).thenReturn(23);
		XMock.when(person.calculateAgeInYear(2014)).thenReturn(24);
		
		assertEquals(Integer.valueOf(23), person.calculateAgeInYear(2013));
		assertEquals(Integer.valueOf(24), person.calculateAgeInYear(2014));
	}
}
