package br.marins;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.marins.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class SpyMethodHandlerTest {

	@Mock private XMockContext context;
	@Mock private MethodContext methodContext;
	
	private Person person = new Person();
	private Method getNameMethod = Person.getMethodGetName();
	
	@Test
	public void invoke() throws Exception {
		Mockito.when(context.getMethodContext(person, getNameMethod)).thenReturn(methodContext);
		
		SpyMethodHandler handler = new SpyMethodHandler(context);
		handler.setSpiedObject(person);
		
		handler.invoke(person, getNameMethod, null, new Object[0]);
		
		Mockito.verify(context).getMethodContext(person, getNameMethod);
		Mockito.verify(methodContext).execute();
	}
	
}
