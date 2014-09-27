package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.matcher.TestUtil.expectAssertionError;
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
	});
}}
