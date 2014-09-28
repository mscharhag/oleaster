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

import com.mscharhag.oleaster.matcher.util.Expectations;

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
		Expectations.expectTrue(this.value == other, "Expected %s to equal %s", this.value, other);
	}

	/**
	 * Checks if the stored value is {@code true}.
	 * <p>This method throws an {@code AssertionError} if the stored value is {@code false}.
	 */
	public void toBeTrue() {
		Expectations.expectTrue(this.value, "Expected %s to be true", this.value);
	}

	/**
	 * Checks if the stored value is {@code false}.
	 * <p>This method throws an {@code AssertionError} if the stored value is {@code true}.
	 */
	public void toBeFalse() {
		Expectations.expectTrue(!this.value, "Expected %s to be false", this.value);
	}
}
