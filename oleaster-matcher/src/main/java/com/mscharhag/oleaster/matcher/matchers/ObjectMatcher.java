package com.mscharhag.oleaster.matcher.matchers;


import com.mscharhag.oleaster.matcher.util.Expectations;

import static com.mscharhag.oleaster.matcher.util.Expectations.expectTrue;
import static com.mscharhag.oleaster.matcher.util.Expectations.expectNotNull;

/**
 * Matcher class to validate Objects of type {@code T}.
 */
public class ObjectMatcher<T> {

	private T value;

	public ObjectMatcher(T value) {
		this.value = value;
	}

	/**
	 * Checks if the stored value is equal to {@code other}.
	 * <p>This method throws an {@code AssertionError} if the stored value is not equal to {@code other}.
	 * @param other the value to compare with
	 */
	public void toEqual(T other) {
		if (this.value == null && other == null) {
			return;
		}
		expectNotNull(this.value, "Expected null to be equal '%s'", other);
		expectNotNull(other, "Expected '%s' to be equal null", this.value);
		expectTrue(this.value.equals(other), "Expected '%s' to be equal '%s'", this.value, other);
	}

	/**
	 * Checks if the stored value is {@code null}.
	 * <p>This method throws an {@code AssertionError} if the stored value is {@code null}.
	 */
	public void toBeNull() {
		expectTrue(this.value == null, "Expected '%s' to be null", this.value);
	}

	/**
	 * Checks if the stored value is not {@code null}.
	 * <p>This method throws an {@code AssertionError} if the stored value is not {@code null}.
	 */
	public void toBeNotNull() {
		expectTrue(this.value != null, "Expected null to be not null");
	}

	protected T getValue() {
		return this.value;
	}
}
