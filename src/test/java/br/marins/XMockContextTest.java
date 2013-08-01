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
public class XMockContextTest {
	
	@Mock private Map<Object, ObjectContext> objectContexts;
	@Mock private ObjectContext personContext;
	
	private Person person = new Person();
	private Method getNameMethod = Person.getMethodGetName();
	private XMockContext context;
	
	@Test
	public void getMethodContext() {
		context.getMethodContext(person, getNameMethod);
		
		Mockito.verify(objectContexts).get(person);
		Mockito.verify(personContext).getMethodContext(getNameMethod);
	}
	
	@Test
	public void getObjectContext() {
		context.getObjectContext(person);
		
		Mockito.verify(objectContexts).get(person);
	}
	
	@Test
	public void createContextPutTheContextInTheMap() {
		ObjectContext personContext = context.createContext(person);
		
		Mockito.verify(objectContexts).put(person, personContext);
	}
	
	@Test
	public void createContextForPerson() {
		ObjectContext personContext = context.createContext(person);
		
		Assert.assertEquals(person, personContext.getObject());
	}
	
	@Before
	public void setup() {
		Mockito.when(objectContexts.get(person)).thenReturn(personContext);
		context = new XMockContext(objectContexts);
	}
}
