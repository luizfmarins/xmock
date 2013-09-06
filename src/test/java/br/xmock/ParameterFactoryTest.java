package br.xmock;

import static br.xmock.util.Assert.assertOnlyOne;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class ParameterFactoryTest {

	private static final Object[] PARAMETER = new Object[] { Integer.valueOf(1) };

	@Test
	public void createRealParameter() {
		List<Parameter> parameter = ParameterFactory.getInstance().createForMock(PARAMETER);
		
		assertTrue(assertOnlyOne(parameter) instanceof RealParameter);
	}
	
	@Test
	public void createAnyObjectParameter() {
		ParameterFactory.getInstance().setUseAnyObject();
		
		List<Parameter> parameter = ParameterFactory.getInstance().createForMock(PARAMETER);
		
		assertTrue(assertOnlyOne(parameter) instanceof AnyObjectParameter);
	}
	
	@Test
	public void createForMockSetUsingRealParameter() {
		ParameterFactory.getInstance().setUseAnyObject();
		
		ParameterFactory.getInstance().createForMock(PARAMETER);
		
		assertFalse(ParameterFactory.getInstance().isUsingAnyObject());
	}
	
	@Test
	public void createForRealCallDoesNotAlterUsingRealParameter() {
		ParameterFactory.getInstance().setUseAnyObject();
		
		ParameterFactory.getInstance().createForRealCall(PARAMETER);
		
		assertTrue(ParameterFactory.getInstance().isUsingAnyObject());
	}
	
	@Test
	public void createForRealCallDoesNotConsiderUsingRealParameter() {
		ParameterFactory.getInstance().setUseAnyObject();
		
		List<Parameter> parameter = ParameterFactory.getInstance().createForRealCall(PARAMETER);
		
		assertTrue(assertOnlyOne(parameter) instanceof RealParameter);
	}
}
