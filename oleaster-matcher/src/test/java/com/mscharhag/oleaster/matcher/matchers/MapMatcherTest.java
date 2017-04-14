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
                expectAssertionError(() -> new MapMatcher(map).toBeEmpty(),
                    "Expected '{one=1, two=2}' to be empty");
            });

            it("is ok if the stored map is empty", () -> {
                new MapMatcher(new HashMap()).toBeEmpty();
            });
        });

        describe("when toNotBeEmpty() is called", () -> {
            it("fails if the stored map is empty", () -> {
                expectAssertionError(() -> new MapMatcher(new HashMap()).toNotBeEmpty(),
                    "Expected '{}' to not be empty");
            });

            it("is ok if the stored map is not empty", () -> {
                new MapMatcher(map).toNotBeEmpty();
            });
        });

        describe("when toContainKey() is called", () -> {
            it("fails if the stored map does not contain the expected key", () -> {
                expectAssertionError(() -> new MapMatcher(map).toContainKey("three"),
                    "Expected '{one=1, two=2}' to contain key 'three'");
            });

            it("is ok if the stored map contains the expected key", () -> {
                new MapMatcher(map).toContainKey("one");
            });
        });

        describe("when toNotContainKey() is called", () -> {
            it("fails if the stored map does not contain the expected key", () -> {

            });

            it("is ok if the stored map contains the expected key", () -> {

            });
        });

        describe("when toContainValue() is called", () -> {
            it("fails if the stored map does not contain the expected value", () -> {

            });

            it("is ok if the stored map contains the expected value", () -> {

            });
        });

        describe("when toNotContainValue() is called", () -> {
            it("fails if the stored value does not contain the expected value", () -> {

            });

            it("is ok if the stored value contains the expected value", () -> {

            });
        });

        describe("when toHaveLength() is called", () -> {
            it("fails if the stored value does not have the provided length", () -> {

            });

            it("is ok if the stored value does have the provided length", () -> {

            });
        });

    });
}}
