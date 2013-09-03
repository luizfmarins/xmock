package br.xmock;

import java.lang.reflect.Method;
import java.util.Arrays;

class MethodCall {

	private final Method method;
	private final Object[] params;
	
	MethodCall(Method method, Object[] params) {
		this.method = method;
		this.params = params;
	}
	
	public Method getMethod() {
		return method;
	}

	public Object[] getParams() {
		return params;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + Arrays.hashCode(params);
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
		
		if (!Arrays.equals(params, other.params))
			return false;
		
		return true;
	}
}
