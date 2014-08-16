package com.mscharhag.oleaster.matcher;

public class Matchers {

	public static IntMatcher expect(int value) {
		return new IntMatcher(value);
	}
}
