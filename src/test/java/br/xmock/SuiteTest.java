package br.xmock;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ReturnPromiseMethodHandlerTest.class,
	ActualReturnPromiseTest.class,
	DelegateToRealInstanceMethodCallTest.class, 
	SpyMethodHandlerTest.class,
	SpyMethodFilterTest.class,
	MethodCallMockTest.class,
	MethodCallTest.class,
	XMockTest.class
})
public class SuiteTest {

}
