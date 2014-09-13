package com.mscharhag.oleaster.matcher;

import org.junit.Assert;

public class IntegerNumberMatcher {

	private long value;

	public IntegerNumberMatcher(long value) {
		this.value = value;
	}

	public void toEqual(long other) {
		Assert.assertEquals(other, this.value);
	}

	public void toEqual(int other) {
		this.toEqual((long) other);
	}

	public void toEqual(short value) {
		this.toEqual((long) value);
	}

	public void toEqual(byte value) {
		this.toEqual((long) value);
	}
}
