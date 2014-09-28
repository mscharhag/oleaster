/*
* Copyright 2014 Michael Scharhag
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.util.Arguments;
import com.mscharhag.oleaster.matcher.util.Expectations;

/**
 * Matcher class to validate floating point numbers numbers
 */
public class FloatingPointNumberMatcher {

	private double value;

	/**
	 * Creates a new FloatingPointNumberMatcher to validate to passed {@code value}
	 * @param value the value that should be validated
	 */
	public FloatingPointNumberMatcher(double value) {
		this.value = value;
	}

	/**
	 * Checks if the stored value is equal to another value.
	 * <p>This method throws an {@code AssertionError} if the stored value is not equal to {@code other}.
	 * <p><b>Note:</b>In most situations floating point numbers should not be checked for equality.
	 * If you want to check if the stored value is nearly the same as another value use
	 * {@link #toBeCloseTo(double, double)} instead.
	 * @param other the value to compare with
	 * @see #toBeCloseTo(double, double)
	 */
	public void toEqual(double other) {
		Expectations.expectTrue(this.value == other, "Expected %s to equal %s", this.value, other);
	}

	/**
	 * Checks if the stored value is close to another value.
	 * <p>This method throws an {@code AssertionError} if the difference between the stored value and {@code other} is
	 * greater than {@code delta}.
	 * @param other the value to compare with
	 * @param delta the delta
	 */
	public void toBeCloseTo(double other, double delta) {
		boolean isCloseTo = this.value >= other - delta && this.value <= other + delta;
		Expectations.expectTrue(isCloseTo, "Expected %s to be close to %s with delta %s", this.value, other, delta);
	}

	/**
	 * Checks if the stored value is close to another value.
	 * <p>Similar to {@link #toBeCloseTo(double, double)} but uses a default delta of {@code 0.00001}.
	 * <p>This method throws an {@code AssertionError} if the difference between the stored value and {@code other} is
	 * greater than {@code 0.00001}.
	 * @param other the value to compare with
	 */
	public void toBeCloseTo(double other) {
		this.toBeCloseTo(other, 0.00001);
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
	public void toBeGreaterThan(double other) {
		Expectations.expectTrue(this.value > other, "Expected %s to be greater than %s", this.value, other);
	}

	/**
	 * Checks if the stored value is smaller than another value.
	 * <p>This method throws an {@code AssertionError} if:
	 * <ul>
	 *   	<li>the stored value is greater than {@code other}</li>
	 *   	<li>the stored value is equal to {@code other}</li>
	 * </ul>
	 * @param other the value to compare with
	 */
	public void toBeSmallerThan(double other) {
		Expectations.expectTrue(this.value < other, "Expected %s to be smaller than %s", this.value, other);
	}

	/**
	 * Checks if the stored value is between a lower and an upper bound.
	 * <p>This method throws an {@code AssertionError} if:
	 * <ul>
	 *     <li>the stored value is smaller than the lower bound</li>
	 *     <li>the stored value is greater than the upper bound</li>
	 * </ul>
	 * <p>It is ok if the stored value is equal to the lower or the upper bound
	 * @param lower the lower bound
	 * @param upper the upper bound
	 * @throws java.lang.IllegalArgumentException if {@code lower} is not smaller than {@code upper}
	 */
	public void toBeBetween(double lower, double upper) {
		Arguments.ensureTrue(lower < upper, "upper has to be greater than lower");
		boolean isBetween = this.value >= lower && this.value <= upper;
		Expectations.expectTrue(isBetween, "Expected %s to be between %s and %s", this.value, lower, upper);
	}
}
