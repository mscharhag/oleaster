package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.util.Assertions;

/**
 * Matcher class to validate boolean values
 */
public class BooleanMatcher {

	private boolean value;

	/**
	 * Creates a new BooleanMatcher that can be used to validate the passed {@code value}.
	 * @param value the value that should be validated
	 */
	public BooleanMatcher(boolean value) {
		this.value = value;
	}

	/**
	 * Checks if the stored value is equal to another value.
	 * <p>This method throws an {@code AssertionError} if the stored value is not equal to {@code other}.
	 * @param other the value to compare with
	 */
	public void toEqual(boolean other) {
		Assertions.failIfFalse(this.value == other, "Expected %s to equal %s", this.value, other);
	}

	/**
	 * Checks if the stored value is {@code true}.
	 * <p>This method throws an {@code AssertionError} if the stored value is {@code false}.
	 */
	public void toBeTrue() {
		Assertions.failIfFalse(this.value, "Expected %s to be true", this.value);
	}

	/**
	 * Checks if the stored value is {@code false}.
	 * <p>This method throws an {@code AssertionError} if the stored value is {@code true}.
	 */
	public void toBeFalse() {
		Assertions.failIfFalse(!this.value, "Expected %s to be false", this.value);
	}
}
