package br.marins;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;

public class SpyMethodHandler implements MethodHandler {

	private Object spiedObject;
	private XMockContext context;
	
	public SpyMethodHandler() {
		this.context = XMockContext.getInstance();
	}
	
	protected SpyMethodHandler(XMockContext context) {
		this.context = context;
	}
	
	@Override
	public Object invoke(Object self, Method currentMethod, Method proceedMethod, Object[] args) {
		return context.getMethodContext(self, currentMethod).execute();
	}
	
	public void setSpiedObject(Object spiedObject) {
		this.spiedObject = spiedObject;
	}

}
