package br.xmock;

import java.util.ArrayList;
import java.util.List;

public class ParameterFactory {

	private static final ParameterFactory instance = new ParameterFactory();
	
	private boolean isUsingAnyObject;
	
	private ParameterFactory() {}

	public List<Parameter> createForMock(Object[] parameterObjects) {
		List<Parameter> params = isUsingAnyObject ? createAnyObjectParameters(parameterObjects) : createRealParameters(parameterObjects);
		
		isUsingAnyObject = false;
		
		return params;
	}

	public List<Parameter> createForRealCall(Object[] parameterObjects) {
		return createRealParameters(parameterObjects);
	}
	
	void setUseAnyObject() {
		isUsingAnyObject = true;
	}
	
	boolean isUsingAnyObject() {
		return isUsingAnyObject;
	}
	
	List<Parameter> createRealParameters(Object[] parameterObjects) {
		List<Parameter> params = new ArrayList<Parameter>();
		
		for(Object obj : parameterObjects)
			params.add(new RealParameter(obj));
			
		return params;
	}
	
	List<Parameter> createAnyObjectParameters(Object[] parameterObjects) {
		List<Parameter> params = new ArrayList<Parameter>();
		
		for(Object obj : parameterObjects)
			params.add(new AnyObjectParameter(obj.getClass()));
			
		return params;
	}
	
	public static ParameterFactory getInstance() {
		return instance;
	}
}
