package br.xmock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class MethodCallTest {

	private final ParameterFactory parameterFactory = ParameterFactory.getInstance();
	
	@Test
	public void equals() {
		MethodCall call1 = new MethodCall(Person.getMethodCalculateAgeInYear(), parameterFactory.createForMock(new Object[] {2013}));
		MethodCall call2 = new MethodCall(Person.getMethodCalculateAgeInYear(), parameterFactory.createForMock(new Object[] {2013}));
		
		assertTrue(call1.equals(call2));
	}
	
	@Test
	public void notEqualsByParameters() {
		MethodCall call1 = new MethodCall(Person.getMethodCalculateAgeInYear(), parameterFactory.createForMock(new Object[] {2013}));
		MethodCall call2 =  new MethodCall(Person.getMethodCalculateAgeInYear(), parameterFactory.createForMock(new Object[] {2014}));
		
		assertFalse(call1.equals(call2));
	}
	
	@Test
	public void notEqualsByMethod() {
		MethodCall call1 = new MethodCall(Person.getMethodGetAge(), parameterFactory.createForMock(new Object[] {2014}));
		MethodCall call2 = new MethodCall(Person.getMethodCalculateAgeInYear(), parameterFactory.createForMock(new Object[] {2014}));
		
		assertFalse(call1.equals(call2));
	}
	
	@Test
	public void equalsAnyIntRealParameter() {
		MethodCall call1 = new MethodCall(Person.getMethodGetAge(), parameterFactory.createAnyObjectParameters(new Object[] {1}));
		
		MethodCall call2 = new MethodCall(Person.getMethodGetAge(), parameterFactory.createRealParameters(new Object[] {2014}));
		
		assertTrue(call1.equals(call2));
	}
	
	@Test
	public void notEqualsAnyInt() {
		MethodCall call1 = new MethodCall(Person.getMethodGetAge(), parameterFactory.createAnyObjectParameters(new Object[] {1}));
		
		MethodCall call2 = new MethodCall(Person.getMethodGetAge(), parameterFactory.createRealParameters(new Object[] {"abc"}));
		
		assertFalse(call1.equals(call2));
	}
	
	@Test
	public void notEqualsAnyIntAnyString() {
		MethodCall call1 = new MethodCall(Person.getMethodGetAge(), parameterFactory.createAnyObjectParameters(new Object[] {1}));
		
		MethodCall call2 = new MethodCall(Person.getMethodGetAge(), parameterFactory.createAnyObjectParameters(new Object[] {"abc"}));
		
		assertFalse(call1.equals(call2));
	}
	
	@Test
	public void equalsAnyIntAnyInt() {
		MethodCall call1 = new MethodCall(Person.getMethodGetAge(), parameterFactory.createAnyObjectParameters(new Object[] {1}));
		MethodCall call2 = new MethodCall(Person.getMethodGetAge(), parameterFactory.createAnyObjectParameters(new Object[] {2}));
		
		assertTrue(call1.equals(call2));
	}
}
