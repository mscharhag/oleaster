package com.mscharhag.oleaster.matcher;

import org.junit.Assert;

public class FloatingPointNumberMatcher {

	private double value;

	public FloatingPointNumberMatcher(double value) {
		this.value = value;
	}

	public void toEqual(double other) {
		Assert.assertEquals(other, this.value);
	}

	public void toEqual(float other) {
		this.toEqual((double) other);
	}

}
