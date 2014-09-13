package com.mscharhag.oleaster.matcher;

public class Matchers {

	public static IntegerNumberMatcher expect(long value) {
		return new IntegerNumberMatcher(value);
	}

	public static IntegerNumberMatcher expect(int value) {
		return new IntegerNumberMatcher(value);
	}

	public static IntegerNumberMatcher expect(short value) {
		return new IntegerNumberMatcher(value);
	}

	public static IntegerNumberMatcher expect(byte value) {
		return new IntegerNumberMatcher(value);
	}

	public static FloatingPointNumberMatcher expect(double value) {
		return new FloatingPointNumberMatcher(value);
	}

	public static FloatingPointNumberMatcher expect(float value) {
		return new FloatingPointNumberMatcher(value);
	}

	public static ExceptionMatcher expect(ExceptionMatcher.CodeBlock block) {
		return new ExceptionMatcher(block);
	}

	public static BooleanMatcher expect(boolean value) {
		return new BooleanMatcher(value);
	}
}
