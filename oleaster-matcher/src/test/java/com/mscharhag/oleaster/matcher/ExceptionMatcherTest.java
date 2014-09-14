package com.mscharhag.oleaster.matcher;

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

			it("does not throw an error if it is checked for the correct exception class", () -> {
				matcher.toFailWith(IllegalArgumentException.class);
			});

			it("does not throw an error if it is checked for a super class of the thrown exception", () -> {
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
