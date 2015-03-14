package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.TestUtil;
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

			it("fails if it is checked for a different exception class", () -> {
				TestUtil.expectAssertionError(() -> matcher.toThrow(NullPointerException.class));
			});

			it("is ok if it is checked for the correct exception class", () -> {
				matcher.toThrow(IllegalArgumentException.class);
			});

			it("is ok if it is checked with the correct exception class and the correct message", () -> {
				matcher.toThrow(IllegalArgumentException.class, "test exception");
			});

			it("fails if the exception message is not equal to the expected message", () -> {
                String expectedMessage = "Expected exception message 'foo' but was 'test exception'";
                TestUtil.expectAssertionError(() -> matcher.toThrow(IllegalArgumentException.class, "foo"), expectedMessage);
			});

			it("is ok if it is checked for a super class of the thrown exception", () -> {
				matcher.toThrow(RuntimeException.class);
			});
		});

		describe("when the passed code block throws no exception", () -> {
			beforeEach(() -> {
				matcher = new ExceptionMatcher(() -> {});
			});

			it("fails if it is checked for an exception", () -> {
				TestUtil.expectAssertionError(() -> matcher.toThrow(IllegalArgumentException.class));
			});

			it("fails if it is checked for an exception and a message", () -> {
				TestUtil.expectAssertionError(() -> matcher.toThrow(IllegalArgumentException.class, "foo"));
			});
		});

	});
}}
