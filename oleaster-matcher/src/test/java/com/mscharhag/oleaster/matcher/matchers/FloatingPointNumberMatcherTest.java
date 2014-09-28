package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.TestUtil;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.assertTrue;

@RunWith(OleasterRunner.class)
public class FloatingPointNumberMatcherTest {{
	describe("FloatingPointNumberMatcher test", () -> {

		describe("when toEqual() is called", () -> {
			it("fails if both values are not equal", () -> {
				TestUtil.expectAssertionError(() -> new FloatingPointNumberMatcher(1.5).toEqual(2.5),
						"Expected 1.5 to equal 2.5");
			});

			it("is ok if both values are equal", () -> {
				new FloatingPointNumberMatcher(1.5).toEqual(1.5);
			});
		});

		describe("when toBeGreaterThan() is called", () -> {
			it("fails if the passed value greater", () -> {
				TestUtil.expectAssertionError(() -> new FloatingPointNumberMatcher(1.5).toBeGreaterThan(1.6),
						"Expected 1.5 to be greater than 1.6");
			});

			it("fails if both values are equal", () -> {
				TestUtil.expectAssertionError(() -> new FloatingPointNumberMatcher(1.5).toBeGreaterThan(1.5),
						"Expected 1.5 to be greater than 1.5");
			});

			it("is ok if the passed value is smaller", () -> {
				new FloatingPointNumberMatcher(1.5).toBeGreaterThan(1.4);
			});
		});

		describe("when toBeSmallerThan() is called", () -> {
			it("fails if the passed value smaller", () -> {
				TestUtil.expectAssertionError(() -> new FloatingPointNumberMatcher(1.5).toBeSmallerThan(1.4),
						"Expected 1.5 to be smaller than 1.4");
			});

			it("fails if both values are equal", () -> {
				TestUtil.expectAssertionError(() -> new FloatingPointNumberMatcher(1.5).toBeSmallerThan(1.5),
						"Expected 1.5 to be smaller than 1.5");
			});

			it("is ok if the passed value is greater", () -> {
				new FloatingPointNumberMatcher(1.5).toBeSmallerThan(1.6);
			});
		});

		describe("when toBeBetween() is called", () -> {
			it("throws an exception if the lower bound is greater than the upper bound", () -> {
				Exception ex = TestUtil.catchException(() -> new FloatingPointNumberMatcher(1.5).toBeBetween(1.6, 1.4));
				assertTrue(ex instanceof IllegalArgumentException);
			});

			it("throws an exception if the lower bound is equal to the upper bound", () -> {
				Exception ex = TestUtil.catchException(() -> new FloatingPointNumberMatcher(1.5).toBeBetween(1.4, 1.4));
				assertTrue(ex instanceof IllegalArgumentException);
			});

			it("fails if the value smaller than the lower bound", () -> {
				TestUtil.expectAssertionError(() -> new FloatingPointNumberMatcher(1.5).toBeBetween(1.6, 1.7),
						"Expected 1.5 to be between 1.6 and 1.7");
			});

			it("is ok if the value is equal to the lower bound", () -> {
				new FloatingPointNumberMatcher(1.5).toBeBetween(1.5, 1.6);
			});

			it("is ok if the value is greater than the lower bound and lower than the upper bound", () -> {
				new FloatingPointNumberMatcher(1.5).toBeBetween(1.4, 1.6);
			});

			it("is ok if the value is equal to the upper bound", () -> {
				new FloatingPointNumberMatcher(1.5).toBeBetween(1.4, 1.5);
			});

			it("fails if the value is greater than the upper bound", () -> {
				TestUtil.expectAssertionError(() -> new FloatingPointNumberMatcher(1.5).toBeBetween(1.3, 1.4),
						"Expected 1.5 to be between 1.3 and 1.4");
			});
		});

		describe("when toBeCloseTo() without delta parameter is used", () -> {
			it("is ok if the value is greater but the delta is smaller than 0.00001", () -> {
				new FloatingPointNumberMatcher(1.500009).toBeCloseTo(1.5);
			});

			it("is ok if the value is smaller but the delta is smaller than 0.00001", () -> {
				new FloatingPointNumberMatcher(1.49999).toBeCloseTo(1.5);
			});

			it("fails if the the delta of 0.00001 is exceeded", () -> {
				TestUtil.expectAssertionError(() -> new FloatingPointNumberMatcher(1.4999).toBeCloseTo(1.5),
						"Expected 1.4999 to be close to 1.5 with delta 1.0E-5");
			});
		});

		describe("when toBeCloseTo() with a delta parameter is used", () -> {
			it("is ok if the delta is smaller than the passed value", () -> {
				new FloatingPointNumberMatcher(1.549).toBeCloseTo(1.5, 0.05);
			});

			it("fails if the the delta is exceeded", () -> {
				TestUtil.expectAssertionError(() -> new FloatingPointNumberMatcher(1.551).toBeCloseTo(1.5, 0.05),
						"Expected 1.551 to be close to 1.5 with delta 0.05");
			});
		});

	});
}}
