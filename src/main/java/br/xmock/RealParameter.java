package br.xmock;

class RealParameter extends Parameter {

	private final Object object;
	
	protected RealParameter(Object object) {
		this.object = object;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		return result;
	}

	@Override
	protected Class<?> getParameterClass() {
		return object.getClass();
	}
	
	protected Object getObject() {
		return object;
	}
}
