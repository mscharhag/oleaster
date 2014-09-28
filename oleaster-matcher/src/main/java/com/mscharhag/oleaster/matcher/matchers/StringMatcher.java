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
import static com.mscharhag.oleaster.matcher.util.Expectations.*;

import java.util.regex.Pattern;

/**
 * Matcher class to validate Strings
 */
public class StringMatcher extends ObjectMatcher<String> {


	public StringMatcher(String value) {
		super(value);
	}

	/**
	 * Checks if the stored {@code String} matches a regular expression.
	 * <p>This method throws an {@code AssertionError} if
	 * <ul>
	 *		<li>the stored {@code String} does not match the passed {@code pattern}</li>
	 * 		<li>the stored {@code String} is {@code null}</li>
	 * </ul>
	 * @param pattern the {@code Pattern} representing the regular expression
	 * @throws java.lang.IllegalArgumentException if {@code pattern} is {@code null}.
	 */
	public void toMatch(Pattern pattern) {
		Arguments.ensureNotNull(pattern, "pattern cannot be null");
		expectNotNull(this.getValue(), "Expected null to match '%s'", pattern.pattern());
		expectTrue(pattern.matcher(this.getValue()).matches(),
				"Expected '%s' to match '%s'", this.getValue(), pattern.pattern());
	}

	/**
	 * Checks if the stored {@code String} matches a regular expression.
	 * <p>This method is similar to {@link #toMatch(java.util.regex.Pattern)} except it takes the regular expression as
	 * String. The passed String {@code pattern} will be compiled to a {@link java.util.regex.Pattern} using
	 * {@code Pattern.compile()}.
	 * @param pattern a {@code String} containing the regular expression.
	 */
	public void toMatch(String pattern) {
		this.toMatch(Pattern.compile(pattern));
	}

	/**
	 * Checks if the stored {@code String} starts with a given sub string.
	 * <p>This method throws an {@code AssertionError} if
	 * <ul>
	 *		<li>the stored {@code String} does not start with {@code start}</li>
	 * 		<li>the stored {@code String} is {@code null}</li>
	 * </ul>
	 * @throws java.lang.IllegalArgumentException if {@code start} is {@code null}.
	 * @param start the sub string
	 */
	public void toStartWith(String start) {
		Arguments.ensureNotNull(start, "start cannot be null");
		expectNotNull(this.getValue(), "Expected null to start with '%s'", start);
		expectTrue(this.getValue().startsWith(start), "Expected '%s' to start with '%s'", this.getValue(), start);
	}

	/**
	 * Checks if the stored {@code String} ends with a given sub string.
	 * <p>This method throws an {@code AssertionError} if
	 * <ul>
	 *		<li>the stored {@code String} does not end with {@code end}</li>
	 * 		<li>the stored {@code String} is {@code null}</li>
	 * </ul>
	 * @throws java.lang.IllegalArgumentException if {@code end} is {@code null}.
	 * @param end the sub string
	 */
	public void toEndWith(String end) {
		Arguments.ensureNotNull(end, "end cannot be null");
		expectNotNull(this.getValue(), "Expected null to end with '%s'", end);
		expectTrue(this.getValue().endsWith(end), "Expected '%s' to end with '%s'", this.getValue(), end);
	}


	/**
	 * Checks if the stored {@code String} contains a given sub string.
	 * <p>This method throws an {@code AssertionError} if
	 * <ul>
	 *		<li>the stored {@code String} does not contain {@code substr}</li>
	 * 		<li>the stored {@code String} is {@code null}</li>
	 * </ul>
	 * @throws java.lang.IllegalArgumentException if {@code substr} is {@code null}.
	 * @param substr the sub string
	 */
	public void toContain(String substr) {
		Arguments.ensureNotNull(substr, "substr cannot be null");
		expectNotNull(this.getValue(), "Expected null to contain '%s'", substr);
		expectTrue(this.getValue().contains(substr), "Expected '%s' to contain '%s'", this.getValue(), substr);
	}

}
