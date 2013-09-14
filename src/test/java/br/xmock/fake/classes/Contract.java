package br.xmock.fake.classes;

public class Contract {

	private Person person;
	
	public Contract(Person person) {
		this.person = person;
	}
	
	public String getOwnerName() {
		return person.getName();
	}
}
