package br.xmock;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodFilter;

class SpyMethodFilter implements MethodFilter {

	@Override
	public boolean isHandled(Method method) {
		return !isMethodDeclaredInObject(method);
	}

	private boolean isMethodDeclaredInObject(Method method) {
		for (Method objectMethods : Object.class.getMethods())
			if (objectMethods.equals(method))
				return true;
		
		return false;
	}

}
