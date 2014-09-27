package com.mscharhag.oleaster.matcher.matchers;

import static com.mscharhag.oleaster.matcher.TestUtil.*;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class StringMatcherTest {{
	describe("StringMatcher test", () -> {

		describe("when toMatch() is called", () -> {
			it("fails if the stored value does not match the expected pattern", () -> {
				expectAssertionError(() -> new StringMatcher("foo").toMatch(Pattern.compile("bar")),
						"Expected 'foo' to match 'bar'");
			});

			it("fails if the stored value is null", () -> {
				expectAssertionError(() -> new StringMatcher(null).toMatch(Pattern.compile("bar")),
						"Expected null to match 'bar'");
			});

			it("is ok if the stored value matches the expected pattern", () -> {
				new StringMatcher("foo").toMatch(Pattern.compile("fo{2}"));
			});

			it("creates a pattern instance if the pattern is passed as string", () -> {
				new StringMatcher("foo").toMatch("fo{2}");
			});
		});


		describe("when toStartWith() is called", () -> {
			it("fails if the stored value is null", () -> {
				expectAssertionError(() -> new StringMatcher(null).toStartWith("bar"), "Expected null to start with 'bar'");
			});

			it("fails if the stored value does not start with the expected sub string", () -> {
				expectAssertionError(() -> new StringMatcher("foo").toStartWith("fee"), "Expected 'foo' to start with 'fee'");
			});

			it("is ok if the stored value starts with the expected sub string", () -> {
				new StringMatcher("foo").toStartWith("fo");
			});
		});


		describe("when toEndWith() is called", () -> {
			it("fails if the stored value is null", () -> {
				expectAssertionError(() -> new StringMatcher(null).toEndWith("bar"), "Expected null to end with 'bar'");
			});

			it("fails if the stored value does not end with the expected sub string", () -> {
				expectAssertionError(() -> new StringMatcher("foo").toEndWith("fee"), "Expected 'foo' to end with 'fee'");
			});

			it("is ok if the stored value ends with the expected sub string", () -> {
				new StringMatcher("foo").toEndWith("oo");
			});
		});


		describe("when toContain() is called", () -> {
			it("fails if the stored value is null", () -> {
				expectAssertionError(() -> new StringMatcher(null).toContain("bar"), "Expected null to contain 'bar'");
			});

			it("fails if the stored value does not contain the passed value", () -> {
				expectAssertionError(() -> new StringMatcher("foo").toContain("a"), "Expected 'foo' to contain 'a'");
			});

			it("is ok if the stored value contains the passed value", () -> {
				new StringMatcher("foo").toContain("o");
			});
		});
	});
}}
