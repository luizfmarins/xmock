package br.marins;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectContext {

	private Object object;
	private Map<Method, MethodContext> methodsContext = new HashMap<Method, MethodContext>();
	
	public ObjectContext(Object object) {
		this.object = object;
	}
	
	protected ObjectContext(Map<Method, MethodContext> methodsContext) {
		this.methodsContext = methodsContext;
	}
	
	public MethodContext getMethodContext(Method method) {
		return methodsContext.get(method);
	}
	
	protected Object getObject() {
		return object;
	}
	
}
