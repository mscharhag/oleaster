package com.mscharhag.oleaster.matcher.util;

public class Arguments {

	public static void ensureNotNull(Object value, String format, Object... args) {
		ensureNotNull(value, String.format(format, args));
	}

	public static void ensureNotNull(Object value, String message) {
		if (value == null) {
			throw new IllegalArgumentException(message);
		}
	}
}
