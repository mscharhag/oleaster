package com.mscharhag.oleaster.matcher;

import org.junit.Assert;

public class BooleanMatcher {

	private boolean value;

	public BooleanMatcher(boolean value) {
		this.value = value;
	}

	public void toEqual(boolean other) {
		Assert.assertEquals(other, this.value);
	}

	public void toBeTrue() {
		Assert.assertTrue(this.value);
	}

	public void toBeFalse() {
		Assert.assertFalse(this.value);
	}
}
