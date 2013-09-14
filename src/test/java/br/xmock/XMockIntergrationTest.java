package br.xmock;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.xmock.fake.classes.Contract;
import br.xmock.fake.classes.NaturalPerson;
import br.xmock.fake.classes.Person;

public class XMockIntergrationTest {

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
	
	@Test
	public void spyMethodWithParameters() {
		Person person = XMock.spy(new Person());
		
		XMock.doReturn(23).when(person).calculateAgeInYear(2013);
		XMock.doReturn(24).when(person).calculateAgeInYear(2014);
		
		assertEquals(Integer.valueOf(23), person.calculateAgeInYear(2013));
		assertEquals(Integer.valueOf(24), person.calculateAgeInYear(2014));
	}
	
	@Test
	public void anyObjectSetParameterFactory() {
		Person person = XMock.spy(new Person());
		
		XMock.doReturn(23).when(person).calculateAgeInYear(XMock.anyInt());
		
		assertEquals(Integer.valueOf(23), person.calculateAgeInYear(60));
	}
	
	@Test
	public void mockMethodWithAnyInt() {
		Person person = XMock.mock(Person.class);
		
		XMock.when(person.calculateAgeInYear(XMock.anyInt())).thenReturn(23);
		
		assertEquals(Integer.valueOf(23), person.calculateAgeInYear(0));
		assertEquals(Integer.valueOf(23), person.calculateAgeInYear(1000));
	}
	
	@Test
	public void verify() {
		Person person = XMock.mock(Person.class);
		
		Contract contract = new Contract(person);
		contract.getOwnerName();
		
		XMock.verify(person).getName();
	}
	
	@Test
	public void verifyRannyDay() {
		Person person = XMock.mock(Person.class);
		
		exception.expect(VerifyMethodCallException.class);
		exception.expectMessage("Wanted to be invoked 1 time(s): public java.lang.String br.xmock.fake.classes.Person.getName()");
		XMock.verify(person).getName();
	}
	
	@Test
	public void verifyMethodCallTwoTimesRannyDay() {
		Person person = XMock.mock(Person.class);
		
		person.getName();
		
		exception.expect(VerifyMethodCallException.class);
		exception.expectMessage("Wanted to be invoked 2 time(s): public java.lang.String br.xmock.fake.classes.Person.getName()");
		XMock.verify(person, XMock.times(2)).getName();
	}
	
	@Test
	public void verifyMethodCallTwoTimesHappyDay() {
		Person person = XMock.mock(Person.class);

		person.getName();
		person.getName();
		
		XMock.verify(person, XMock.times(2)).getName();
	}
	
	@Test
	public void verifyMethodCallWithParameter() {
		Person person = XMock.mock(Person.class);
		
		person.calculateAgeInYear(2013);
		
		XMock.verify(person).calculateAgeInYear(2013);
	}
	
	@Test
	public void verifyMethodCallWithParameterRannyDay() {
		Person person = XMock.mock(Person.class);
		
		person.calculateAgeInYear(2013);
		
		exception.expect(VerifyMethodCallException.class);
		XMock.verify(person).calculateAgeInYear(2014);
	}
}