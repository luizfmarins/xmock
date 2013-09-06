package br.xmock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class MethodCallFactoryTest {

	@Mock private ParameterFactory parameterFactory;
	private MethodCallFactory factory;
	
	@Test
	public void createForMockUsesCreateForMockOnParameterFactory() {
		factory.createForMock(Person.getMethodCalculateAgeInYear(), new Object[] {Integer.valueOf(1)});
		
		Mockito.verify(parameterFactory).createForMock(new Object[] {Integer.valueOf(1)});
	}
	
	@Test
	public void createForRealCallUsesCreateForRealCallOnParameterFactory() {
		factory.createForRealCall(Person.getMethodCalculateAgeInYear(), new Object[] {Integer.valueOf(1)});
		
		Mockito.verify(parameterFactory).createForRealCall(new Object[] {Integer.valueOf(1)});
	}
	
	@Before
	public void setup() {
		factory = new MethodCallFactory(parameterFactory);
	}
	
}
