package com.mscharhag.oleaster.matcher;

import org.junit.Assert;

import java.util.regex.Pattern;

public class StringMatcher extends ObjectMatcher<String> {


	public StringMatcher(String value) {
		super(value);
	}

	public void toEqual(String other) {
		this.failIfNull("Expected null to be equal '%s'", other);
		if (other == null) {
			this.fail("Expected '%s' to be equal null", this.getValue());
		}
		String message = String.format("Expected '%s' to be equal '%s'", this.getValue(), other);
		Assert.assertTrue(message, this.getValue().equals(other));
	}

	public void toMatch(Pattern pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("pattern cannot be null");
		}
		this.failIfNull("Expected null to match '%s'", pattern.pattern());
		String message = String.format("Expected '%s' to match '%s'", this.getValue(), pattern.pattern());
		Assert.assertTrue(message, pattern.matcher(this.getValue()).matches());
	}

	public void toMatch(String pattern) {
		this.toMatch(Pattern.compile(pattern));
	}

	public void toStartWith(String start) {
		if (start == null) {
			throw new IllegalArgumentException("start cannot be null");
		}
		this.failIfNull("Expected null to start with '%s'", start);
		String message = String.format("Expected '%s' to start with '%s'", this.getValue(), start);
		Assert.assertTrue(message, this.getValue().startsWith(start));
	}

	public void toEndWith(String end) {
		if (end == null) {
			throw new IllegalArgumentException("end cannot be null");
		}
		this.failIfNull("Expected null to end with '%s'", end);
		String message = String.format("Expected '%s' to end with '%s'", this.getValue(), end);
		Assert.assertTrue(message, this.getValue().endsWith(end));
	}

	public void toContain(String substr) {
		if (substr == null) {
			throw new IllegalArgumentException("substr cannot be null");
		}
		this.failIfNull("Expected null to contain '%s'", substr);
		String message = String.format("Expected '%s' to contain '%s'", this.getValue(), substr);
		Assert.assertTrue(message, this.getValue().contains(substr));

	}

}
