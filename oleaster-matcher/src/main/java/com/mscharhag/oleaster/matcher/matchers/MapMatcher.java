package com.mscharhag.oleaster.matcher.matchers;

import java.util.Map;

import com.mscharhag.oleaster.matcher.util.Arguments;

import static com.mscharhag.oleaster.matcher.util.Expectations.expectTrue;

public class MapMatcher extends ObjectMatcher<Map> {
	public MapMatcher(final Map value) {
		super(value);
		Arguments.ensureNotNull(value, "Map cannot be null");
	}

	public void toBeEmpty() {
	  expectTrue(this.getValue().size() == 0, "Expected '%s' to be empty", this.getValue());
	}

	public void toNotBeEmpty() {
		expectTrue(this.getValue().size() > 0, "Expected '%s' to not be empty", this.getValue());
	}

	public void toContainKey(final Object key) {
		expectTrue(this.getValue().containsKey(key), "Expected '%s' to contain key '%s'", this.getValue(), key);
	}

	public void toNotContainKey(final Object key) {
		expectTrue(!this.getValue().containsKey(key), "Expected '%s' to not contain key '%s'", this.getValue(), key);
	}

	public void toContainValue(final Object value) {
		expectTrue(this.getValue().containsValue(value), "Expected '%s' to contain value '%s'", this.getValue(), value);
	}

	public void toNotContainValue(final Object value) {
		expectTrue(!this.getValue().containsValue(value), "Expected '%s' to not contain value '%s'", this.getValue(), value);
	}

	public void toHaveLength(final int length) {
		expectTrue(this.getValue().size() == length, "Expected '%s' to have a length of %d, instead has a length of %d", this.getValue(), length, this.getValue().size());
	}
}
