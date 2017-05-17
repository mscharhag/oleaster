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
package com.mscharhag.oleaster.matcher;

import com.mscharhag.oleaster.matcher.matchers.*;
import com.mscharhag.oleaster.matcher.matchers.datetime.DateMatcher;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * {@code Matchers} provides a set of static {@code expect()} methods that can (and should) be used to
 * obtain Matcher instances.
 * <p>The returned Matcher instances can be used to check test results.
 * <p>For example
 * <pre>
 *     expect(40 + 2).toEqual(42);
 *     expect(person).toBeNotNull();
 *     expect("foo").toStartWith("fo");
 * </pre>
 * <p>For fluent access, it is recommended to statically import the methods of this class.
 */
public class Matchers {

	private Matchers() {
		// no instance needed
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.IntegerNumberMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code long} values.
	 * <pre>
	 *     expect(42l).toEqual(42);
	 *     expect(42l).toBeGreaterThan(41);
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.IntegerNumberMatcher
	 * @param value the value that should be checked
	 * @return a new {@code IntegerNumberMatcher} initialized with {@code value}
	 */
	public static IntegerNumberMatcher expect(long value) {
		return new IntegerNumberMatcher(value);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.IntegerNumberMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code int} values.
	 * <pre>
	 *     expect(42).toEqual(42);
	 *     expect(42).toBeGreaterThan(41);
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.IntegerNumberMatcher
	 * @param value the value that should be checked
	 * @return a new {@code IntegerNumberMatcher} initialized with {@code value}
	 */
	public static IntegerNumberMatcher expect(int value) {
		return new IntegerNumberMatcher(value);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.IntegerNumberMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code short} values.
	 * <pre>
	 *     expect((short) 42).toEqual(42);
	 *     expect((short) 42).toBeGreaterThan(41);
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.IntegerNumberMatcher
	 * @param value the value that should be checked
	 * @return a new {@code IntegerNumberMatcher} initialized with {@code value}
	 */
	public static IntegerNumberMatcher expect(short value) {
		return new IntegerNumberMatcher(value);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.IntegerNumberMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code byte} values.
	 * <pre>
	 *     expect((byte) 42).toEqual(42);
	 *     expect((byte) 42).toBeGreaterThan(41);
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.IntegerNumberMatcher
	 * @param value the value that should be checked
	 * @return a new {@code IntegerNumberMatcher} initialized with {@code value}
	 */
	public static IntegerNumberMatcher expect(byte value) {
		return new IntegerNumberMatcher(value);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.FloatingPointNumberMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code double} values.
	 * <pre>
	 *     expect(42.0).toEqual(42.0);
	 *     expect(41.999 + 0.001).toBeCloseTo(42);
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.FloatingPointNumberMatcher
	 * @param value the value that should be checked
	 * @return a new {@code FloatingPointNumberMatcher} initialized with {@code value}
	 */
	public static FloatingPointNumberMatcher expect(double value) {
		return new FloatingPointNumberMatcher(value);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.FloatingPointNumberMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code float} values.
	 * <pre>
	 *     expect(42.0f).toEqual(42.0);
	 *     expect(41.999f + 0.001f).toBeCloseTo(42);
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.FloatingPointNumberMatcher
	 * @param value the value that should be checked
	 * @return a new {@code FloatingPointNumberMatcher} initialized with {@code value}
	 */
	public static FloatingPointNumberMatcher expect(float value) {
		return new FloatingPointNumberMatcher(value);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.ExceptionMatcher} initialized with
	 * the passed {@code CodeBlock}.
	 * <p>The returned matcher can be used to check if the passed {@code CodeBlock} throw an exception.
	 * {@link com.mscharhag.oleaster.matcher.matchers.ExceptionMatcher.CodeBlock} is a functional interface that can be
	 * implemented using a Java 8 Lambda expression.
	 * <p>Example:
	 * <pre>
	 *     expect(() -&gt; {
	 *         // code that should throw an IllegalArgumentException
	 *     }).toThrow(IllegalArgumentException.class);
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.ExceptionMatcher
	 * @param block the code block that should throw an exception
	 * @return a new {@code ExceptionMatcher} initialized with {@code block}
	 */
	public static ExceptionMatcher expect(ExceptionMatcher.CodeBlock block) {
		return new ExceptionMatcher(block);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.BooleanMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code boolean} values.
	 * <pre>
	 *     expect(true).toEqual(true);
	 *     expect(true).toBeTrue();
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.BooleanMatcher
	 * @param value the value that should be checked
	 * @return a new {@code BooleanMatcher} initialized with {@code value}
	 */
	public static BooleanMatcher expect(boolean value) {
		return new BooleanMatcher(value);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.StringMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code String} values.
	 * <pre>
	 *     expect("foo").toEqual("foo");
	 *     expect("foo").toStartWith("fo");
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.StringMatcher
	 * @param value the value that should be checked
	 * @return a new {@code StringMatcher} initialized with {@code value}
	 */
	public static StringMatcher expect(String value) {
		return new StringMatcher(value);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.CollectionMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code Collection} values.
	 * <pre>
	 *     expect(list).toContain("foo");
	 *     expect(list).toBeEmpty();
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.CollectionMatcher
	 * @param value the value that should be checked
	 * @return a new {@code CollectionMatcher} initialized with {@code value}
	 */
	public static CollectionMatcher expect(Collection value) {
		return new CollectionMatcher(value);
	}

	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.MapMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code Map} values.
	 * <pre>
	 *     expect(map).toContainValue("foo");
	 *     expect(map).toHaveLength(3);
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.MapMatcher
	 * @param value the value that should be checked
	 * @return a new {@code MapMatcher} initialized with {@code value}
	 */
	public static MapMatcher expect(Map value) {
		return new MapMatcher(value);
	}
	/**
	 * Creates a new {@link com.mscharhag.oleaster.matcher.matchers.ObjectMatcher} initialized with
	 * the passed {@code value}.
	 * <p>The returned matcher can be used to check {@code Object} values.
	 * <pre>
	 *     expect(person).toEqual(new Person("john"));
	 *     expect(person).toBeNotNull();
	 * </pre>
	 * @see com.mscharhag.oleaster.matcher.matchers.ObjectMatcher
	 * @param value the value that should be checked
	 * @return a new {@code ObjectMatcher} initialized with {@code value}
	 */
	public static ObjectMatcher<Object> expect(Object value) {
		return new ObjectMatcher<>(value);
	}


	public static DateMatcher expect(Date date) {
		return new DateMatcher(date);
	}
}
