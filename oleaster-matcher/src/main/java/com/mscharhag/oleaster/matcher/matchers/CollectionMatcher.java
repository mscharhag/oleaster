package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.util.Arguments;

import java.util.Collection;

import static com.mscharhag.oleaster.matcher.util.Expectations.expectNotNull;
import static com.mscharhag.oleaster.matcher.util.Expectations.expectTrue;

/**
 * Matcher class to validate Collections
 */
public class CollectionMatcher extends ObjectMatcher<Collection> {

    public CollectionMatcher(final Collection value) {
        super(value);
    }

    /**
     * Checks if the stored {@code Collection} contains the provided item.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Collection} does not contain the passed {@code item}</li>
     *     <li>the given {@code Collection} is {@code null}</li>
     * </ul>
     * @param item the {@code Object} which the {@code Collection} should contain.
     */
    public void toContain(final Object item) {
        expectNotNull(this.getValue(), "Expected null to contain '%s'", item);
        expectTrue(this.getValue().contains(item), "Expected '%s' to contain '%s'", this.getValue(), item);
    }

    /**
     * Checks if the stored {@code Collection} does not contain the provided item.
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Collection} does contain the passed {@code item}</li>
     *     <li>the given {@code Collection} is {@code null}</li>
     * </ul>
     * @param item the {@code Object} which the {@code Collection} should not contain.
     */
    public void toNotContain(final Object item) {
        expectNotNull(this.getValue(), "Expected null to not contain '%s'", item);
        expectTrue(!this.getValue().contains(item), "Expected '%s' to not contain '%s'", this.getValue(), item);
    }

    /**
     * Checks if the given {@code Collection} is empty
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Collection} is not empty</li>
     *     <li>the given {@code Collection} is {@code null}</li>
     * </ul>
     */
    public void toBeEmpty() {
        expectNotNull(this.getValue(), "Expected null to be empty");
        expectTrue(this.getValue().isEmpty(), "Expected '%s' to be empty", this.getValue());
    }

    /**
     * Checks if the given {@code Collection} is not empty
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Collection} is empty</li>
     *     <li>the given {@code Collection} is {@code null}</li>
     * </ul>
     */
    public void toNotBeEmpty() {
        expectNotNull(this.getValue(), "Expected null to not be empty");
        expectTrue(!this.getValue().isEmpty(), "Expected '%s' to not be empty", this.getValue());
    }

    /**
     * Checks if the given {@code Collection} has the expected size
     * <p>This method throws an {@code AssertionError} if
     * <ul>
     *     <li>the stored {@code Collection} has a different size than the passed value</li>
     *     <li>the stored {@code Collection} is {@code null}</li>
     * </ul>
     * @param size The expected size of the collection
     */
    public void toHaveSize(final int size) {
        expectNotNull(this.getValue(), "Expected null to have size '%d'", size);
        expectTrue(this.getValue().size() == size, "Expected '%s' to have a size of %d, " +
                "instead has a size of %d", this.getValue(), size, this.getValue().size());
    }
}
