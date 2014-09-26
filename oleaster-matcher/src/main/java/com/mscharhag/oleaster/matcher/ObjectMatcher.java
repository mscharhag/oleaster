package com.mscharhag.oleaster.matcher;

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

	protected void fail(String format, Object args) {
		Assert.fail(String.format(format, args));
	}

	protected void failIfNull(String format, Object... args) {
		if (this.value == null) {
			Assert.fail(String.format(format, args));
		}
	}
}
