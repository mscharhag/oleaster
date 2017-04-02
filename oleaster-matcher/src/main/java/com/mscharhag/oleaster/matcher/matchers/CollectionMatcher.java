package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.util.Arguments;

import java.util.Collection;

import static com.mscharhag.oleaster.matcher.util.Expectations.expectTrue;

/**
 * Matcher class to validate Collections
 */
public class CollectionMatcher extends ObjectMatcher<Collection> {
    public CollectionMatcher(final Collection value) {
        super(value);
        Arguments.ensureNotNull(this.getValue(), "Collection cannot be null");
    }

    /**
     * Checks if the stored {@code Collection} contains the provided item.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Collection} does not contain the passed {@code item}</li>
     * </ul>
     * </p>
     * @param item the {@code Object} which the {@code Collection} should contain.
     */
    public void toContain(final Object item) {
        expectTrue(this.getValue().contains(item), "Expected '%s' to contain '%s'", this.getValue(), item);
    }

    public void toNotContain(final Object item) {
        expectTrue(!this.getValue().contains(item), "Expected '%s' to not contain '%s'", this.getValue(), item);
    }

    public void toBeEmpty() {
        expectTrue(this.getValue().size() == 0, "Expected '%s' to be empty", this.getValue());
    }

    public void toNotBeEmpty() {
        expectTrue(this.getValue().size() > 0, "Expected '%s' to not be empty", this.getValue());
    }

    public void toHaveLength(final int count) {
        expectTrue(this.getValue().size() == count, "Expected '%s' to have a length of %d, instead has a length of %d", this.getValue(), count, this.getValue().size());
    }
}
