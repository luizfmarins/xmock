package br.marins;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.marins.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class ObjectContextTest {

	@Mock private Map<Method, MethodContext> methodsContext;
	@Mock private MethodContext methodContext;
	
	private ObjectContext objectContext;
	
	@Test
	public void getMethodContextSearchInTheMap() {
		objectContext.getMethodContext(Person.getMethodGetName());
		
		Mockito.verify(methodsContext).get(Person.getMethodGetName());
	}
	
	@Test
	public void getMethodContext() {
		MethodContext getNameMethodContext = objectContext.getMethodContext(Person.getMethodGetName());
		
		Assert.assertEquals(methodContext, getNameMethodContext);
	}
	
	@Before
	public void setup() {
		Mockito.when(methodsContext.get(Person.getMethodGetName())).thenReturn(methodContext);
		objectContext = new ObjectContext(methodsContext);
	}
	
}
