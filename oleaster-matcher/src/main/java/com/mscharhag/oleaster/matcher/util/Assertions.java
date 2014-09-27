package com.mscharhag.oleaster.matcher.util;

import org.junit.Assert;

public class Assertions {

	public static void failIfNull(Object value, String message) {
		if (value == null) {
			fail(message);
		}
	}


	public static void failIfNull(Object value, String format, Object... args) {
		failIfNull(value, String.format(format, args));
	}


	public static void failIfFalse(boolean condition, String message) {
		if (!condition) {
			fail(message);
		}
	}


	public static void failIfFalse(boolean condition, String format, Object... args) {
		failIfFalse(condition, String.format(format, args));
	}


	public static void fail(String message) {
		Assert.fail(message);
	}
}
