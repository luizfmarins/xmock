package br.xmock;

class AnyObjectParameter extends Parameter {
	
	private final Class<?> parameterClass;
	
	protected AnyObjectParameter(Class<?> parameterClass) {
		this.parameterClass = parameterClass;
	}
	
	@Override
	protected Class<?> getParameterClass() {
		return parameterClass;
	}
}
