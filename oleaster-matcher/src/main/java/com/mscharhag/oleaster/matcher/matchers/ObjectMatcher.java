package com.mscharhag.oleaster.matcher.matchers;

import org.junit.Assert;

public class ObjectMatcher<T> {

	private T value;

	public ObjectMatcher(T value) {
		this.value = value;
	}

	public void toEqual(Object other) {
		Assert.assertEquals(other, value);
	}

	protected T getValue() {
		return this.value;
	}

	// TODO: toBeNull() ?? toBeNotNull()
}
