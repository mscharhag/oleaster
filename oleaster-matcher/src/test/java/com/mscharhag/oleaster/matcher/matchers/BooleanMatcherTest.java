package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.TestUtil;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class BooleanMatcherTest {
	private BooleanMatcher matcher;
{
	describe("BooleanMatcher tests", () -> {

		describe("when the stored value is true", () -> {
			beforeEach(() -> {
				matcher = new BooleanMatcher(true);
			});

			it("is ok if it is checked for 'true' value",() -> {
				matcher.toBeTrue();
				matcher.toEqual(true);
			});

			it("fails if it is checked for 'false' value", () -> {
				TestUtil.expectAssertionError(matcher::toBeFalse, "Expected true to be false");
				TestUtil.expectAssertionError(() -> matcher.toEqual(false), "Expected true to equal false");
			});
		});

		describe("when the stored value is false", () -> {
			beforeEach(() -> {
				matcher = new BooleanMatcher(false);
			});

			it("fails if it is checked for 'true' value", () -> {
				TestUtil.expectAssertionError(matcher::toBeTrue, "Expected false to be true");
				TestUtil.expectAssertionError(() -> matcher.toEqual(true), "Expected false to equal true");
			});

			it("is ok if it is checked for 'false' value", () -> {
				matcher.toBeFalse();
				matcher.toEqual(false);
			});
		});
	});
}}
