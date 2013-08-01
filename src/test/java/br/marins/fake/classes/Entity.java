package br.marins.fake.classes;

public class Entity {

	protected void field_set(String atributo, Object valor) {
		throw new UnsupportedOperationException("Should never call this method");
	}
	
	protected Object field_get(String atributo) {
		throw new UnsupportedOperationException("Should never call this method");
	}
	
}
