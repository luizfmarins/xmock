package br.xmock;

abstract class Parameter {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (!(obj instanceof Parameter)) return false;
		
		Parameter other = (Parameter) obj;
		
		if (this instanceof AnyObjectParameter) 
			return equalsWithAnyObject((AnyObjectParameter)this, other);
		
		if (other instanceof AnyObjectParameter)
			return equalsWithAnyObject((AnyObjectParameter)other, this);
		
		RealParameter selfReal = (RealParameter) this;
		RealParameter otherReal = (RealParameter) obj;
		
		return selfReal.getObject().equals(otherReal.getObject());
	}

	private boolean equalsWithAnyObject(AnyObjectParameter anyObject, Parameter other) {
		return anyObject.getParameterClass().equals(other.getParameterClass());
	}

	protected abstract Class<?> getParameterClass();
	
}
