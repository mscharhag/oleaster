package com.mscharhag.oleaster.examples;

public class Person {

	private String firstName;
	private String lastName;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Person person = (Person) o;

		if (!firstName.equals(person.firstName)) return false;
		if (!lastName.equals(person.lastName)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		return result;
	}
}
