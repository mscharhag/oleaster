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


import static com.mscharhag.oleaster.matcher.util.Expectations.*;

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

	/**
	 * Checks if the stored value is an instance of the {@code expectedClass}
	 * <p>This method throws an {@code AssertionError} if the stored value is not of instance of {@code expectedClass}.
	 * @param expectedClass The expected class
	 */
	public void toBeInstanceOf(Class<?> expectedClass) {
		expectTrue(expectedClass.isInstance(value), "Expected '%s' to be instance of '%s'", this.value, expectedClass.getName());
	}

	protected T getValue() {
		return this.value;
	}
}
