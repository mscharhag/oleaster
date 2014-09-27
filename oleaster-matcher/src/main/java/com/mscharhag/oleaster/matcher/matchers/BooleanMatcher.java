package com.mscharhag.oleaster.matcher.matchers;

import org.junit.Assert;

/**
 * Matcher class to validate boolean values
 */
public class BooleanMatcher {

	private boolean value;

	public BooleanMatcher(boolean value) {
		this.value = value;
	}

	/**
	 * Checks if the stored value is equal to another value.
	 * <p>This method throws an {@code AssertionError} if the stored value is not equal to {@code other}.
	 * @param other the value to compare with
	 */
	public void toEqual(boolean other) {
		Assert.assertEquals(other, this.value);
	}

	/**
	 * Checks if the stored value is {@code true}.
	 * <p>This method throws an {@code AssertionError} if the stored value is {@code false}.
	 */
	public void toBeTrue() {
		Assert.assertTrue(this.value);
	}

	/**
	 * Checks if the stored value is {@code false}.
	 * <p>This method throws an {@code AssertionError} if the stored value is {@code true}.
	 */
	public void toBeFalse() {
		Assert.assertFalse(this.value);
	}
}
