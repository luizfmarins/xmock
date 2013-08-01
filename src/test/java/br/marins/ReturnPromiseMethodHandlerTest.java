package br.marins;

import javassist.util.proxy.ProxyFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.marins.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class ReturnPromiseMethodHandlerTest {

	@Mock private SpyMethodHandler spyMethodHandler;
	@Mock private ActualReturnPromise returnPromise;
	
	private ReturnPromiseMethodHandler handler;
	
	@Test
	public void invoke() {
		Person person = createProxiedPerson();
		
		handler.invoke(person, Person.getMethodGetName(),null, new Object[0]);
		
		Mockito.verify(spyMethodHandler).addReturnPromise(returnPromise);
	}
	
	@Test
	public void invokeSetTheMethodInTheReturnPromisse() {
		Person person = createProxiedPerson();
		
		handler.invoke(person, Person.getMethodGetName(),null, new Object[0]);
		
		Mockito.verify(returnPromise).setMethod(Person.getMethodGetName());
	}
	
	@Before
	public void setup() {
		handler = new ReturnPromiseMethodHandler(spyMethodHandler, returnPromise);
	}
	
	private Person createProxiedPerson() {
		try {
			ProxyFactory factory = new ProxyFactory();
			factory.setSuperclass(Person.class);
			return (Person) factory.create(new Class[0], new Object[0], new SpyMethodHandler());
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}
	
}
