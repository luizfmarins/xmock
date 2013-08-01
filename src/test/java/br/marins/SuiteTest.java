package br.marins;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ReturnPromiseMethodHandlerTest.class,
	ActualReturnPromiseTest.class,
	NoReturnPromiseTest.class, 
	SpyMethodHandlerTest.class,
	SpyMethodFilterTest.class,
	XMockTest.class
})
public class SuiteTest {

}
