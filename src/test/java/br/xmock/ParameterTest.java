package br.xmock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParameterTest {

	@Test
	public void equalsRealParameters() {
		RealParameter param1 = new RealParameter(1);
		RealParameter param2 = new RealParameter(1);
		
		assertTrue(param1.equals(param2));
	}
	
	@Test
	public void equalsAnyObject() {
		AnyObjectParameter anyObject = new AnyObjectParameter(Integer.class);
		RealParameter parameter = new RealParameter(1);
		
		assertTrue(anyObject.equals(parameter));
		assertTrue(parameter.equals(anyObject));
	}
	
	@Test
	public void equalsAnyObjectParameter() {
		AnyObjectParameter param1 = new AnyObjectParameter(Integer.class);
		AnyObjectParameter param2 = new AnyObjectParameter(Integer.class);
		
		assertTrue(param1.equals(param2));
	}
	
	@Test
	public void notEqualsRealParameters() {
		RealParameter param1 = new RealParameter(1);
		RealParameter param2 = new RealParameter(2);
		
		assertFalse(param1.equals(param2));
	}
	
	@Test
	public void notEqualsAnyObjectParameters() {
		AnyObjectParameter param1 = new AnyObjectParameter(Integer.class);
		AnyObjectParameter param2 = new AnyObjectParameter(String.class);
		
		assertFalse(param1.equals(param2));
	}
	
	@Test
	public void notEqualsAnyObjectParameterAndRealParameter() {
		AnyObjectParameter anyString = new AnyObjectParameter(String.class);
		RealParameter real = new RealParameter(1);
		
		assertFalse(real.equals(anyString));
		assertFalse(anyString.equals(real));
	}
}
