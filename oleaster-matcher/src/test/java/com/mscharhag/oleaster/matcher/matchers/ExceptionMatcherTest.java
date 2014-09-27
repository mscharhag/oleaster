package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.TestUtil;
import com.mscharhag.oleaster.matcher.matchers.ExceptionMatcher;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class ExceptionMatcherTest {
	private ExceptionMatcher matcher;
{
	describe("ExceptionMatcher tests", () -> {

		describe("when the passed code block throws an exception", () -> {
			beforeEach(() -> {
				matcher = new ExceptionMatcher(() -> {
					throw new IllegalArgumentException("test exception");
				});
			});

			it("throws an AssertionError if it is checked for a different exception class", () -> {
				TestUtil.expectAssertionError(() -> matcher.toFailWith(NullPointerException.class));
			});

			it("is ok if it is checked for the correct exception class", () -> {
				matcher.toFailWith(IllegalArgumentException.class);
			});

			it("is ok if it is checked with the correct exception class and the correct message", () -> {
				matcher.toFailWith(IllegalArgumentException.class, "test exception");
			});

			it("throws an AssertionError if the exception message is not equal to the expected message", () -> {
				TestUtil.expectAssertionError(() -> matcher.toFailWith(IllegalArgumentException.class, "foo"));
			});

			it("is ok if it is checked for a super class of the thrown exception", () -> {
				matcher.toFailWith(RuntimeException.class);
			});
		});

		describe("when the passed code block throws no exception", () -> {
			beforeEach(() -> {
				matcher = new ExceptionMatcher(() -> {});
			});

			it("throws a AssertionError if it is checked for an exception", () -> {
				TestUtil.expectAssertionError(() -> matcher.toFailWith(IllegalArgumentException.class));
			});
		});

	});
}}
