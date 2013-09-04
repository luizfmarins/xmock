package br.xmock;

import java.util.ArrayList;
import java.util.List;

public class ParameterFactory {

	private static final ParameterFactory instance = new ParameterFactory();
	
	private boolean isUsingAnyObject;
	
	private ParameterFactory() {}

	public List<Parameter> create(Object[] parameterObjects) {
		List<Parameter> params = isUsingAnyObject ? createAnyObjectParameters(parameterObjects) : createRealParameters(parameterObjects);
		
		isUsingAnyObject = false;
		
		return params;
	}
	
	void setUseAnyObject() {
		isUsingAnyObject = true;
	}
	
	boolean isUsingAnyObject() {
		return isUsingAnyObject;
	}
	
	private List<Parameter> createRealParameters(Object[] parameterObjects) {
		List<Parameter> params = new ArrayList<Parameter>();
		
		for(Object obj : parameterObjects)
			params.add(new RealParameter(obj));
			
		return params;
	}
	
	private List<Parameter> createAnyObjectParameters(Object[] parameterObjects) {
		List<Parameter> params = new ArrayList<Parameter>();
		
		for(Object obj : parameterObjects)
			params.add(new AnyObjectParameter(obj.getClass()));
			
		return params;
	}
	
	public static ParameterFactory getInstance() {
		return instance;
	}
}
