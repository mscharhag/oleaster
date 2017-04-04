package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.runner.OleasterRunner;

import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.matcher.TestUtil.*;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class ObjectMatcherTest {{
	describe("ObjectMatcher tests", () -> {
		describe("when toEqual() is called", () -> {
			it("fails if both values are not equal", () -> {
				expectAssertionError(() -> new ObjectMatcher<>("foo").toEqual("bar"),
						"Expected 'foo' to be equal 'bar'");
			});

			it("fails if the matcher value is null", () -> {
				expectAssertionError(() -> new ObjectMatcher<String>(null).toEqual("bar"),
						"Expected null to be equal 'bar'");
			});

			it("fails if the passed value is null", () -> {
				expectAssertionError(() -> new ObjectMatcher<>("foo").toEqual(null),
						"Expected 'foo' to be equal null");
			});

			it("is ok if both values are equal", () -> {
				new ObjectMatcher<>("foo").toEqual("foo");
			});

			it("is ok if the stored value and the expected value are null", () -> {
				new ObjectMatcher<>(null).toEqual(null);
			});
		});

		describe("when toBeNull() is called", () -> {
			it("fails if the value is not null", () -> {
				expectAssertionError(() -> new ObjectMatcher<>("foo").toBeNull(), "Expected 'foo' to be null");
			});

			it("is ok if the value is null", () -> {
				new ObjectMatcher<>(null).toBeNull();
			});
		});

		describe("when toBeNotNull() is called", () -> {
			it("fails if the value is null", () -> {
				expectAssertionError(() -> new ObjectMatcher<>(null).toBeNotNull(), "Expected null to be not null");
			});

			it("is ok if the value is not null", () -> {
				new ObjectMatcher<>("foo").toBeNotNull();
			});
		});

		describe("when toBeInstanceOf() is called", () -> {
			it("fails if the value is not of instance of input", () -> {
				expectAssertionError(() -> new ObjectMatcher<>("foo").toBeInstanceOf(Integer.class), "Expected 'foo' to be instance of 'java.lang.Integer'");
			});

			it("is ok if the value is instance of the expected class", () -> {
				new ObjectMatcher<>("foo").toBeInstanceOf(String.class);

			});

			it("is ok if the value is of the same class", () -> {
				final Animal john = new Animal("John");
				new ObjectMatcher<>(john).toBeInstanceOf(Animal.class);
			});

			it("is ok if the value is a child of the parent class", () -> {
				final Dog marie = new Dog("Marie");
				new ObjectMatcher<>(marie).toBeInstanceOf(Animal.class);
			});

			it("is not ok if the value is a parent of the child class", () -> {
				final Animal bo = new Animal("Bo");
				expectAssertionError(() -> new ObjectMatcher<>(bo).toBeInstanceOf(Dog.class), "Expected '" + bo + "' to be instance of '" + Dog.class.getName() + "'");
			});

		});
	});
}
	public static class Animal {
		public final String name;
		public Animal(final String name) {
			this.name = name;
		}
	}
	public static class Dog extends Animal {
		public Dog(final String name) {
			super(name);
		}
	}
}
