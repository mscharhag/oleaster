package com.mscharhag.oleaster.matcher.matchers;

import java.util.Map;

import com.mscharhag.oleaster.matcher.util.Arguments;

import static com.mscharhag.oleaster.matcher.util.Expectations.expectNotNull;
import static com.mscharhag.oleaster.matcher.util.Expectations.expectTrue;

/**
 * Matcher class to validate {@link Map}s
 */
public class MapMatcher<K, V> extends ObjectMatcher<Map<K, V>> {

	public MapMatcher(final Map<K, V> value) {
		super(value);
	}

    /**
     * Checks if the stored {@code Map} is empty.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Map} is not empty</li>
     *     <li>the stored {@code Map} is {@code null}</li>
     * </ul>
     */
	public void toBeEmpty() {
	    expectNotNull(this.getValue(), "Expected null to be empty");
	    expectTrue(this.getValue().isEmpty(), "Expected '%s' to be empty", this.getValue());
	}

    /**
     * Checks if the stored {@code Map} is not empty.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Map} is empty</li>
     *     <li>the stored {@code Map} is {@code null}</li>
     * </ul>
     */
	public void toNotBeEmpty() {
        expectNotNull(this.getValue(), "Expected null to not be empty");
		expectTrue(!this.getValue().isEmpty(), "Expected '%s' to not be empty", this.getValue());
	}

    /**
     * Checks if the stored {@code Map} contains the given {@code key}.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Map} does not contain the given {@code key}</li>
     *     <li>the stored {@code Map} is {@code null}</li>
     * </ul>
	 * @param key the expected key
     */
	public void toContainKey(final K key) {
        expectNotNull(this.getValue(), "Expected null to contain the key '%s'", key);
		expectTrue(this.getValue().containsKey(key), "Expected '%s' to contain key '%s'", this.getValue(), key);
	}

    /**
     * Checks if the stored {@code Map} does not contain the given {@code key}.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Map} contains the given {@code key}</li>
     *     <li>the stored {@code Map} is {@code null}</li>
     * </ul>
	 * @param key the expected key
     */
	public void toNotContainKey(final K key) {
        expectNotNull(this.getValue(), "Expected null to not contain the key '%s'", key);
		expectTrue(!this.getValue().containsKey(key), "Expected '%s' to not contain key '%s'", this.getValue(), key);
	}

    /**
     * Checks if the stored {@code Map} contains the given {@code value}.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Map} does not contain the given {@code value}</li>
     *     <li>the stored {@code Map} is {@code null}</li>
     * </ul>
	 * @param value the expected value
     */
	public void toContainValue(final V value) {
        expectNotNull(this.getValue(), "Expected null to contain the value '%s'", value);
		expectTrue(this.getValue().containsValue(value), "Expected '%s' to contain value '%s'", this.getValue(), value);
	}

    /**
     * Checks if the stored {@code Map} does not contain the given {@code value}.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Map} does not contain the given {@code value}</li>
     *     <li>the stored {@code Map} is {@code null}</li>
     * </ul>
	 * @param value the expected value
     */
	public void toNotContainValue(final V value) {
        expectNotNull(this.getValue(), "Expected null to not contain the value '%s'", value);
		expectTrue(!this.getValue().containsValue(value), "Expected '%s' to not contain value '%s'", this.getValue(), value);
	}

    /**
     * Checks if the stored {@code Map} has a given {@code size}.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Map} does not have the given {@code size}</li>
     *     <li>the stored {@code Map} is {@code null}</li>
     * </ul>
	 * @param size the expected size
     */
	public void toHaveSize(final int size) {
        expectNotNull(this.getValue(), "Expected null to have size '%s'", size);
		expectTrue(this.getValue().size() == size, "Expected '%s' to have a size of %d, instead has a size of %d", this.getValue(), size, this.getValue().size());
	}
}
