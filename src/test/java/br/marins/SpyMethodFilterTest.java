package br.marins;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Test;

import br.marins.fake.classes.Person;

public class SpyMethodFilterTest {

	private SpyMethodFilter methodFilter = new SpyMethodFilter();
	
	@Test
	public void methodsDeclaredInObjectAreNotHandled() throws Exception {
		for (Method objectMethod : Object.class.getMethods())
			assertFalse(methodFilter.isHandled(objectMethod));
	}
	
	@Test
	public void hashCodeFromASubclassOfObjectIsNotHandled() {
		assertFalse(methodFilter.isHandled(Person.getMethodHashCode()));
	}
	
	@Test
	public void methodNotDeclaredInObjectIsHandled() {
		assertTrue(methodFilter.isHandled(Person.getMethodGetName()));
	}
}
