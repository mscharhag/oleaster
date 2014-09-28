package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.TestUtil;
import com.mscharhag.oleaster.matcher.matchers.IntegerNumberMatcher;
import com.mscharhag.oleaster.runner.OleasterRunner;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class IntegerNumberMatcherTest {{
	describe("IntegerNumberMatcher tests", () -> {

		describe("when toEqual() is called", () -> {
			it("fails if both values are not equal", () -> {
				TestUtil.expectAssertionError(() -> new IntegerNumberMatcher(1).toEqual(2), "Expected 1 to equal 2");
			});

			it("is ok if both values are equal", () -> {
				new IntegerNumberMatcher(1).toEqual(1);
			});
		});

		describe("when toBeGreaterThan() is called", () -> {
			it("fails if the passed value greater", () -> {
				TestUtil.expectAssertionError(() -> new IntegerNumberMatcher(1).toBeGreaterThan(2),
						"Expected 1 to be greater than 2");
			});

			it("fails if both values are equal", () -> {
				TestUtil.expectAssertionError(() -> new IntegerNumberMatcher(1).toBeGreaterThan(1),
						"Expected 1 to be greater than 1");
			});

			it("is ok if the passed value is smaller", () -> {
				new IntegerNumberMatcher(1).toBeGreaterThan(0);
			});
		});

		describe("when toBeSmallerThan() is called", () -> {
			it("fails if the passed value smaller", () -> {
				TestUtil.expectAssertionError(() -> new IntegerNumberMatcher(1).toBeSmallerThan(0),
						"Expected 1 to be smaller than 0");
			});

			it("fails if both values are equal", () -> {
				TestUtil.expectAssertionError(() -> new IntegerNumberMatcher(1).toBeSmallerThan(1),
						"Expected 1 to be smaller than 1");
			});

			it("is ok if the passed value is greater", () -> {
				new IntegerNumberMatcher(1).toBeSmallerThan(2);
			});
		});

		describe("when toBeBetween() is called", () -> {
			it("throws an exception if the lower bound is greater than the upper bound", () -> {
				Exception ex = TestUtil.catchException(() -> new IntegerNumberMatcher(1).toBeBetween(2, 1));
				assertTrue(ex instanceof IllegalArgumentException);
			});

			it("throws an exception if the lower bound is equal to the upper bound", () -> {
				Exception ex = TestUtil.catchException(() -> new IntegerNumberMatcher(1).toBeBetween(2, 2));
				assertTrue(ex instanceof IllegalArgumentException);
			});

			it("fails if the value smaller than the lower bound", () -> {
				TestUtil.expectAssertionError(() -> new IntegerNumberMatcher(1).toBeBetween(2, 3),
						"Expected 1 to be between 2 and 3");
			});

			it("is ok if the value is equal to the lower bound", () -> {
				new IntegerNumberMatcher(1).toBeBetween(1, 2);
			});

			it("is ok if the value is greater than the lower bound and lower than the upper bound", () -> {
				new IntegerNumberMatcher(1).toBeBetween(0, 2);
			});

			it("is ok if the value is equal to the upper bound", () -> {
				new IntegerNumberMatcher(1).toBeBetween(0, 1);
			});

			it("fails if the value is greater than the upper bound", () -> {
				TestUtil.expectAssertionError(() -> new IntegerNumberMatcher(2).toBeBetween(0, 1),
						"Expected 2 to be between 0 and 1");
			});
		});

	});
}}
