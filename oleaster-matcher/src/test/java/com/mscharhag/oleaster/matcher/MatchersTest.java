package com.mscharhag.oleaster.matcher;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static com.mscharhag.oleaster.matcher.Matchers.*;

@RunWith(OleasterRunner.class)
public class MatchersTest {{

	// Just a few tests to make sure matchers can be accessed via expect() as intended.
	// More detailed tests are located in matcher specific test files

	describe("Matchers test", () -> {

		it("IntegerNumberMatcher", () -> {
			expect(40 + 2).toEqual(42);
		});

		it("FloatingPointNumberMatcher", () -> {
			expect(39.666666 + 2.333333).toBeCloseTo(42.0);
		});

		it("ExceptionMatcher", () -> {
			expect(() -> {
				throw new NullPointerException("test");
			}).toFailWith(NullPointerException.class);
		});

		it("BooleanMatcher", () -> {
			expect(true).toBeTrue();
		});

		it("StringMatcher", () -> {
			expect("foo").toEndWith("oo");
		});

	});
}}
