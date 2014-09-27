package com.mscharhag.oleaster.matcher;

import com.mscharhag.oleaster.matcher.matchers.*;

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

	public static StringMatcher expect(String value) {
		return new StringMatcher(value);
	}

	public static ObjectMatcher expect(Object value) {
		return new ObjectMatcher(value);
	}
}
