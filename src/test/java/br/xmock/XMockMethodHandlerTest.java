package br.xmock;

import static org.junit.Assert.assertEquals;

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
	private XMockMethodHandlerStub handler;
	
	@Test
	public void invokeCallsCreateForRealCallOnMethodFactory() throws Throwable {
		handler.invoke(person, Person.getMethodGetAge(), null, new Object[]{});
		
		Mockito.verify(methodCallFactory).createForRealCall(Person.getMethodGetAge(), new Object[0]);
	}
	
	@Test
	public void getTimesCalledWithNoCall() throws Throwable {
		int timesCalled = handler.getTimesCalled(Person.getMethodGetAge(), new Object[0]);
		
		assertEquals(0, timesCalled);
	}
	
	@Test
	public void getTimesCalledWithOneCall() throws Throwable {
		handler.invoke(person, Person.getMethodGetAge(), null, new Object[0]);
		
		int timesCalled = handler.getTimesCalled(Person.getMethodGetAge(), new Object[0]);
		
		assertEquals(1, timesCalled);
	}
	
	@Test
	public void getTimesCalledWithTwoCall() throws Throwable {
		handler.invoke(person, Person.getMethodGetAge(), null, new Object[0]);
		handler.invoke(person, Person.getMethodGetAge(), null, new Object[0]);
		
		int timesCalled = handler.getTimesCalled(Person.getMethodGetAge(), new Object[0]);
		
		assertEquals(2, timesCalled);
	}
	
	@Before
	public void setup() {
		Mockito.when(methodsReturnPromises.get(Mockito.anyObject())).thenReturn(returnPromisse);
		handler = new XMockMethodHandlerStub(methodsReturnPromises, methodCallFactory);
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
