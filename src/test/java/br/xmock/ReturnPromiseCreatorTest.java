package br.xmock;

import static org.junit.Assert.assertEquals;
import javassist.util.proxy.ProxyObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class ReturnPromiseCreatorTest {
	
	@Mock private ProxyObject proxy;
	@Mock private SpyMethodHandler spyMethodHandler;
	@Mock private ReturnPromiseMethodHandler returnPromiseMethodHandler;
	
	private ReturnPromiseCreator creator = ReturnPromiseCreator.newInstance(RETURN_PROMISSE);
	
	private static final String RETURN_PROMISSE = "Promisse";
	
	@Test
	public void create() throws Throwable {
		ReturnPromise returnPromisse = creator.create(Person.getMethodGetName(), new Object[]{});
		
		Object actualReturn = returnPromisse.getReturn(Person.getMethodGetName(), new Object[]{});
		
		assertEquals(RETURN_PROMISSE, actualReturn);
	}
	
	@Test
	public void whenSetsTheHandlerWithAReturnPromisseMethodHandler() {
		creator = Mockito.spy(creator);
		Mockito.doReturn(returnPromiseMethodHandler).when(creator).newReturnPromiseMethodHandler(spyMethodHandler, creator);
		
		creator.when(proxy);
		
		Mockito.verify(proxy).setHandler(returnPromiseMethodHandler);
	}
	
	@Before
	public void setup() {
		Mockito.when(proxy.getHandler()).thenReturn(spyMethodHandler);
		
	}
}
