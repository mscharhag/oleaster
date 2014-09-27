package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.TestUtil;
import com.mscharhag.oleaster.matcher.matchers.BooleanMatcher;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class BooleanMatcherTest {
	private BooleanMatcher matcher;
{
	describe("BooleanMatcher tests", () -> {

		describe("when the passed value is true", () -> {
			beforeEach(() -> {
				matcher = new BooleanMatcher(true);
			});

			it("does not throw an error if it is checked for 'true' value",() -> {
				matcher.toBeTrue();
				matcher.toEqual(true);
			});

			it("throws an AssertionError if it is checked for 'false' value", () -> {
				TestUtil.expectAssertionError(matcher::toBeFalse);
				TestUtil.expectAssertionError(() -> matcher.toEqual(false));
			});
		});

		describe("when the passed value is false", () -> {
			beforeEach(() -> {
				matcher = new BooleanMatcher(false);
			});

			it("throws an AssertionError if it is checked for 'true' value", () -> {
				TestUtil.expectAssertionError(matcher::toBeTrue);
				TestUtil.expectAssertionError(() -> matcher.toEqual(true));
			});

			it("does not throw an error if it is checked for 'false' value", () -> {
				matcher.toBeFalse();
				matcher.toEqual(false);
			});
		});
	});
}}
