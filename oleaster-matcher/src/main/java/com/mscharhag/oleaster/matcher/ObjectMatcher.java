package com.mscharhag.oleaster.matcher;

import org.junit.Assert;

public class ObjectMatcher {

	private Object value;

	public ObjectMatcher(Object value) {
		this.value = value;
	}

	public void toEqual(Object other) {
		Assert.assertEquals(other, value);
	}
}
