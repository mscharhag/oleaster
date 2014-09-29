package com.mscharhag.oleaster.examples;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static com.mscharhag.oleaster.matcher.Matchers.*;

/**
 * This test shows how Oleaster Matchers can be used.
 * These examples are also shown on the Oleaster Matcher documentation page.
 * If changes in this file are made, the documentation needs be updated.
 */
@RunWith(OleasterRunner.class)
public class MatcherExamplesTest {{

	it("gives a quick overview", () -> {

		// same as JUnit's assertEquals(40 + 2, 42)
		expect(40 + 2).toEqual(42);

		// see if a String matches a regular expression
		expect("foobar").toMatch("fo{2}\\w+");

		// test exceptions with Java 8 Lambdas
		expect(() -> {
			// code that throws IllegalArgumentException
			throw new IllegalArgumentException();
		}).toThrow(IllegalArgumentException.class);
	});

	it("shows how numbers can be validated", () -> {
		int value = 42;

		// check for exact value
		expect(value).toEqual(42);

		// check for greater/smaller values
		expect(value).toBeGreaterThan(41);
		expect(value).toBeSmallerThan(43);

		// check for range
		expect(value).toBeBetween(40, 45);

		// floating point number can often not be precisely represented by float/double values.
		// So make sure to compare them with an absolute error (delta)
		expect(42.0000001).toBeCloseTo(42); // uses default delta of 0.00001
		expect(42.0000001).toBeCloseTo(42, 0.000001);
	});

	it("shows how boolean values can be validated", () -> {
		boolean value = true;

		// check for a given parameter
		expect(value).toEqual(true);

		// check if true
		expect(value).toBeTrue();

		// check if false
		value = false;
		expect(value).toBeFalse();
	});

	it("shows how Objects can be validated", () -> {
		Person person = new Person("John", "Smith");

		// check for equality, delegates to Person.equals()
		expect(person).toEqual(new Person("John", "Smith"));

		// check if not null
		expect(person).toBeNotNull();

		// check if null
		person = null;
		expect(person).toBeNull();
	});

	it("shows how Strings can be validated", () -> {
		// check for exact value
		expect("foo").toEqual("foo");

		// check string starting/ending
		expect("foobar").toStartWith("foo");
		expect("foobar").toEndWith("bar");

		// check if a String contains a given substring
		expect("foobar").toContain("oba");

		// check if a String matches a regular expression
		expect("foobar").toMatch(Pattern.compile("fo+\\w*"));
		expect("foobar").toMatch("fo+\\w*");
	});

	it("shows how Exceptions can be tested", () -> {
		// check if an exception is thrown
		expect(() -> {
			// code that throws IllegalArgumentException
			throw new IllegalArgumentException();
		}).toThrow(IllegalArgumentException.class);

		// with exception message
		expect(() -> {
			// code that throws IllegalArgumentException
			throw new IllegalArgumentException("An argument is invalid");
		}).toThrow(IllegalArgumentException.class, "An argument is invalid");
	});

}}
