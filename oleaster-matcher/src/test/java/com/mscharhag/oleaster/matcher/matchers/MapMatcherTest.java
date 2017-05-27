package com.mscharhag.oleaster.matcher.matchers;

import java.util.HashMap;
import java.util.Map;

import com.mscharhag.oleaster.runner.OleasterRunner;

import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.matcher.TestUtil.expectAssertionError;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class MapMatcherTest {{
    describe("MapMatcher test", () -> {

        final Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        describe("when toBeEmpty() is called", () -> {
            it("fails if the stored map is not empty", () -> {
                expectAssertionError(() -> new MapMatcher<>(map).toBeEmpty(),
                    "Expected '{one=1, two=2}' to be empty");
            });

            it("fails if the stored map is null", () -> {
                expectAssertionError(() -> new MapMatcher<>(null).toBeEmpty(),
                    "Expected null to be empty");
            });

            it("is ok if the stored map is empty", () -> {
                new MapMatcher<>(new HashMap<String, Integer>()).toBeEmpty();
            });
        });

        describe("when toNotBeEmpty() is called", () -> {
            it("fails if the stored map is empty", () -> {
                expectAssertionError(() -> new MapMatcher<>(new HashMap<String, Integer>()).toNotBeEmpty(),
                    "Expected '{}' to not be empty");
            });

            it("fails if the stored map is null", () -> {
                expectAssertionError(() -> new MapMatcher<>(null).toNotBeEmpty(),
                        "Expected null to not be empty");
            });

            it("is ok if the stored map is not empty", () -> {
                new MapMatcher<>(map).toNotBeEmpty();
            });
        });

        describe("when toContainKey() is called", () -> {
            it("fails if the stored map does not contain the expected key", () -> {
                expectAssertionError(() -> new MapMatcher<>(map).toContainKey("three"),
                    "Expected '{one=1, two=2}' to contain key 'three'");
            });

            it("fails if the stored map is null", () -> {
                expectAssertionError(() -> new MapMatcher<>(null).toContainKey("one"),
                        "Expected null to contain the key 'one'");
            });

            it("is ok if the stored map contains the expected key", () -> {
                new MapMatcher<>(map).toContainKey("one");
            });
        });

        describe("when toNotContainKey() is called", () -> {
            it("fails if the stored map does not contain the expected key", () -> {
                expectAssertionError(() -> new MapMatcher<>(map).toNotContainKey("one"),
                        "Expected '{one=1, two=2}' to not contain key 'one'");
            });

            it("fails if the stored map is null", () -> {
                expectAssertionError(() -> new MapMatcher<>(null).toNotContainKey("one"),
                        "Expected null to not contain the key 'one'");
            });

            it("is ok if the stored map contains the expected key", () -> {
                new MapMatcher<>(map).toNotContainKey("three");
            });
        });

        describe("when toContainValue() is called", () -> {
            it("fails if the stored map does not contain the expected value", () -> {
                expectAssertionError(() -> new MapMatcher<>(map).toContainValue(3),
                        "Expected '{one=1, two=2}' to contain value '3'");
            });

            it("fails if the stored map is null", () -> {
                expectAssertionError(() -> new MapMatcher<>(null).toContainValue(1),
                        "Expected null to contain the value '1'");
            });

            it("is ok if the stored map contains the expected value", () -> {
                new MapMatcher<>(map).toContainValue(2);
            });
        });

        describe("when toNotContainValue() is called", () -> {
            it("fails if the stored value does not contain the expected value", () -> {
                expectAssertionError(() -> new MapMatcher<>(map).toNotContainValue(1),
                        "Expected '{one=1, two=2}' to not contain value '1'");
            });

            it("fails if the stored map is null", () -> {
                expectAssertionError(() -> new MapMatcher<>(null).toNotContainValue(1),
                        "Expected null to not contain the value '1'");
            });

            it("is ok if the stored value contains the expected value", () -> {
                new MapMatcher<>(map).toNotContainValue(3);
            });
        });

        describe("when toHaveSize() is called", () -> {
            it("fails if the stored value does not have the provided size", () -> {
                expectAssertionError(() -> new MapMatcher<>(map).toHaveSize(3),
                        "Expected '{one=1, two=2}' to have a size of 3, instead has a size of 2");
            });

            it("fails if the stored map is null", () -> {
                expectAssertionError(() -> new MapMatcher<>(null).toHaveSize(1),
                        "Expected null to have size '1'");
            });

            it("is ok if the stored value does have the provided size", () -> {
                new MapMatcher<>(map).toHaveSize(2);
            });
        });

    });
}}
