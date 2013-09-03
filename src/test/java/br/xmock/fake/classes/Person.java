package br.xmock.fake.classes;

import java.lang.reflect.Method;

public class Person extends Entity {
	
	public void setName(String name) {
		field_set("name", name);
	}

	public String getName() {
		return (String) field_get("name");
	}
	
	public void setAge(Integer age) {
		field_set("age", age);
	}
	
	public Integer getAge() {
		return (Integer) field_get("age");
	}
	
	public Integer calculateAgeInYear(Integer year) {
		throw new UnsupportedOperationException("Not Implemented");
	}
	
	public static Method getMethodGetName() {
		return getMethod("getName");
	}
	
	public static Method getMethodGetAge() {
		return getMethod("getAge");
	}

	public static Method getMethodHashCode() {
		return getMethod("hashCode");
	}
	
	public static Method getMethodCalculateAgeInYear() {
		return getMethod("calculateAgeInYear", new Class[] {Integer.class});
	}
	
	private static Method getMethod(String methodName) {
		return getMethod(methodName, new Class[0]);
	}
	
	private static Method getMethod(String methodName, Class<?>[] parameterTypes) {
		try {
			return Person.class.getMethod(methodName, parameterTypes);
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}
}
