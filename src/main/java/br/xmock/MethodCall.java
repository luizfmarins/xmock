package br.xmock;

import java.lang.reflect.Method;
import java.util.List;

class MethodCall {

	private final Method method;
	private final List<Parameter> params;
	
	MethodCall(Method method, List<Parameter> params) {
		this.method = method;
		this.params = params;
	}
	
	public Method getMethod() {
		return method;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (!(obj instanceof MethodCall)) return false;
		
		MethodCall other = (MethodCall) obj;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		
		if (!(params.equals(other.params)))
			return false;
		
		return true;
	}
}
