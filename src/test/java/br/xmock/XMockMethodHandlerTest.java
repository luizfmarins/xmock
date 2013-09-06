package br.xmock;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.xmock.fake.classes.Person;

@RunWith(MockitoJUnitRunner.class)
public class XMockMethodHandlerTest {

	@Mock private Map<MethodCall, ReturnPromise> methodsReturnPromises;
	@Mock private MethodCallFactory methodCallFactory;
	@Mock private ReturnPromise returnPromisse;
	@Mock private Person person;
	
	@Test
	public void invokeCallsCreateForRealCallOnMethodFactory() throws Throwable {
		XMockMethodHandlerStub handler = new XMockMethodHandlerStub(methodsReturnPromises, methodCallFactory);
		
		handler.invoke(person, Person.getMethodGetAge(), null, new Object[]{});
		
		Mockito.verify(methodCallFactory).createForRealCall(Person.getMethodGetAge(), new Object[]{});
	}
	
	@Before
	public void setup() {
		Mockito.when(methodsReturnPromises.get(Mockito.anyObject())).thenReturn(returnPromisse);
	}
	
	private static class XMockMethodHandlerStub extends XMockMethodHandler {

		XMockMethodHandlerStub(Map<MethodCall, ReturnPromise> methodsReturnPromises, MethodCallFactory methodCallFactory) {
			super(methodsReturnPromises, methodCallFactory);
		}
		
		@Override
		protected ReturnPromise getReturnPromisse(MethodCall method) {
			return methodsReturnPromises.get(method);
		}
	}
}
