package com.mscharhag.oleaster.matcher;

import org.junit.Assert;

public class StringMatcher {

	private String value;

	public StringMatcher(String value) {
		this.value = value;
	}

	public void toEqual(String other) {
		Assert.assertEquals(other, this.value);
	}
}
