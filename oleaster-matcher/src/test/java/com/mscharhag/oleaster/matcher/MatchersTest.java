package com.mscharhag.oleaster.matcher;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
			}).toThrow(NullPointerException.class);
		});

		it("BooleanMatcher", () -> {
			expect(true).toBeTrue();
		});

		it("StringMatcher", () -> {
			expect("foo").toEndWith("oo");
		});

		it("Date", () -> {
			Long millis = System.currentTimeMillis();
			Integer delta = 5;
			Date date = new Date(millis);
			Date dateWithinDelta = new Date(millis+delta-1);
			expect(date).toBeCloseTo(dateWithinDelta, delta);
		});

		it("CollectionMatcher", () -> {
			final List<String> list = new LinkedList<>();
			list.add("one");
			list.add("two");
			expect(list).toContain("one");
		});

		it("MapMatcher", () -> {
			final Map<String, Boolean> map = new HashMap<>();
			map.put("one", false);
			map.put("two", true);
			expect(map).toContainKey("one");
			expect(map).toContainValue(true);
		});
	});
}}
