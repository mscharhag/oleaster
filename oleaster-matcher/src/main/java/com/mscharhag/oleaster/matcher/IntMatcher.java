package com.mscharhag.oleaster.matcher;

import org.junit.Assert;

public class IntMatcher {

	private int value;

	public IntMatcher(int value) {
		this.value = value;
	}

	public void toEqual(int other) {
		Assert.assertEquals(other, this.value);
	}
}
