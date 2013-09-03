package br.xmock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.xmock.fake.classes.Person;

public class MethodCallTest {

	@Test
	public void equals() {
		MethodCall call1 = new MethodCall(Person.getMethodCalculateAgeInYear(), new Object[] {2013});
		MethodCall call2 = new MethodCall(Person.getMethodCalculateAgeInYear(), new Object[] {2013});
		
		assertTrue(call1.equals(call2));
	}
	
	@Test
	public void notEqualsByParameters() {
		MethodCall call1 = new MethodCall(Person.getMethodCalculateAgeInYear(), new Object[] {2013});
		MethodCall call2 =  new MethodCall(Person.getMethodCalculateAgeInYear(), new Object[] {2014});
		
		assertFalse(call1.equals(call2));
	}
	
	@Test
	public void notEqualsByMethod() {
		MethodCall call1 = new MethodCall(Person.getMethodGetAge(), new Object[] {2014});
		MethodCall call2 = new MethodCall(Person.getMethodCalculateAgeInYear(), new Object[] {2014});
		
		assertFalse(call1.equals(call2));
	}
}
