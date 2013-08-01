package br.marins;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class XMockContext {

	private Map<Object, ObjectContext> objectContexts = new HashMap<Object, ObjectContext>();
	
	private static final XMockContext context = new XMockContext();
	
	public XMockContext() {}
	
	protected XMockContext(Map<Object, ObjectContext> objectContexts) {
		this.objectContexts = objectContexts;
	}
	
	public MethodContext getMethodContext(Object object, Method method) {
		return getObjectContext(object).getMethodContext(method);
	}

	public ObjectContext getObjectContext(Object object) {
		return objectContexts.get(object);
	}
	
	public ObjectContext createContext(Object object) {
		ObjectContext objectContext = new ObjectContext(object);
		
		objectContexts.put(object, objectContext);
		
		return objectContext;
	}
	
	public static XMockContext getInstance() {
		return context;
	}
}
