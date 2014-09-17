package com.mscharhag.oleaster.matcher;

import org.junit.Assert;

/**
 * Matcher class to validate integer numbers
 */
public class IntegerNumberMatcher {

	private long value;

	/**
	 * Creates a new matcher object that stores the passed value.
	 * @param value the value to store
	 */
	public IntegerNumberMatcher(long value) {
		this.value = value;
	}

	/**
	 * Checks if the stored value is equal to another value.
	 * <p>This method throws an {@code AssertionError} if the stored value is not equal to {@code other}.
	 *
	 * @param other the value to compare with
	 */
	public void toEqual(long other) {
		Assert.assertEquals(other, this.value);
	}

	/**
	 * Checks if the stored value is greater than another value.
	 * <p>This method throws an {@code AssertionError} if:
	 * <ul>
	 * 		<li>the stored value is smaller than {@code other}</li>
	 * 		<li>the stored value is equal to {@code other}</li>
	 * </ul>
	 * @param other the value to compare with
	 */
	public void toBeGreaterThan(long other) {
		Assert.assertTrue(this.value > other);
	}

	/**
	 * Checks if the stored value is smaller than another value.
	 * <p>This method throws an {@code AssertionError} if:
	 * <ul>
	 *     	<li>the stored value is greater than {@code other}</li>
	 * 		<li>the stored value is equal to {@code other}</li>
	 * </ul>
	 * @param other the value to compare with
	 */
	public void toBeSmallerThan(long other) {
		Assert.assertTrue(this.value < other);
	}

	/**
	 * Checks if the stored value is between a lower and an upper bound.
	 * <p>This method throws an {@code AssertionError} if:
	 * <ul>
	 * 		<li>the stored value is smaller than the lower bound</li>
	 * 		<li>the stored value is greater than the upper bound</li>
	 * </ul>
	 * <p>It is ok if the stored value is equal to the lower or the upper bound.
	 * @param lower the lower bound
	 * @param upper the upper bound
	 * @throws java.lang.IllegalArgumentException if {@code lower} is not smaller than {@code upper}
	 */
	public void toBeBetween(long lower, long upper) {
		if (lower >= upper) {
			throw new IllegalArgumentException("upper has to be greater than lower");
		}
		Assert.assertTrue(this.value >= lower && this.value <= upper);
	}

}
