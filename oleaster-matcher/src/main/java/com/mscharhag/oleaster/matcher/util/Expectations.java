package com.mscharhag.oleaster.matcher.util;

public class Expectations {

	public static void expectNotNull(Object value, String message) {
		if (value == null) {
			fail(message);
		}
	}


	public static void expectNotNull(Object value, String format, Object... args) {
		expectNotNull(value, String.format(format, args));
	}


	public static void expectTrue(boolean condition, String message) {
		if (!condition) {
			fail(message);
		}
	}


	public static void expectTrue(boolean condition, String format, Object... args) {
		expectTrue(condition, String.format(format, args));
	}


	public static void fail(String message) {
		if (message == null) {
			throw new AssertionError();
		}
		throw new AssertionError(message);
	}
}
