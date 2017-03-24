package com.mscharhag.oleaster.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.mscharhag.oleaster.runner.suite.Spec;
import com.mscharhag.oleaster.runner.suite.Suite;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;

import static org.junit.Assert.*;

public class HandlerTest {

    private List<String> calls;
    private Function<String, Invokable> block = (String name) -> () -> { calls.add(name); };
    private OleasterRunner runner;
    private Suite suite;
    private Spec first;
    private Spec second;
    private Spec spec;

    public static class TestClass { }


    @Before
    public void before() throws Exception {
        calls = new ArrayList<>();
        runner = new OleasterRunner(TestClass.class);
        suite = new Suite(null, "suite");
        first = new Spec(suite, "spec", Optional.of(block.apply("first-spec")));
        second = new Spec(suite, "spec", Optional.of(block.apply("second-spec")));
        spec = new Spec(suite, "spec", Optional.of(block.apply("spec")));
    }


    @Test
    public void itExecutesBeforeHandlersBeforeTheFirstSpecIsExecuted() {
        suite.addBeforeHandler(block.apply("before"));
        suite.addSpec(first);
        suite.addSpec(second);
        runner.runChild(first, new RunNotifier());
        runner.runChild(second, new RunNotifier());
        assertEquals(Arrays.asList("before", "first-spec", "second-spec"), calls);
    }


    @Test
    public void itExecutesAfterHandlersOnceAfterTheLastSpecIsExecuted() {
        suite.addAfterHandler(block.apply("after"));
        suite.addSpec(first);
        suite.addSpec(second);
        runner.runChild(first, new RunNotifier());
        runner.runChild(second, new RunNotifier());
        assertEquals(Arrays.asList("first-spec", "second-spec", "after"), calls);
    }


    @Test
    public void itExecutesOuterBeforeHandlersBeforeInnerOnes() {
        suite.addBeforeHandler(block.apply("outerBefore"));
        Suite child = new Suite(suite, "child");
        child.addBeforeHandler(block.apply("innerBefore"));
        runner.runChild(new Spec(child, "spec", Optional.of(block.apply("spec"))), new RunNotifier());
        assertEquals(Arrays.asList("outerBefore", "innerBefore", "spec"), calls);
    }


    @Test
    public void itExecutesBeforeHandlersInSpecifiedOrder() {
        suite.addBeforeHandler(block.apply("first"));
        suite.addBeforeHandler(block.apply("second"));
        runner.runChild(spec, new RunNotifier());
        assertEquals(Arrays.asList("first", "second", "spec"), calls);
    }


    @Test
    public void itExecutesBeforeEachHandlersInSpecifiedOrder() {
        suite.addBeforeEachHandler(block.apply("first"));
        suite.addBeforeEachHandler(block.apply("second"));
        runner.runChild(new Spec(suite, "spec", Optional.of(() -> {})), new RunNotifier());
        assertEquals(Arrays.asList("first", "second"), calls);
    }


    @Test
    public void itExecutesAfterEachHandlersInSpecifiedOrder() {
        suite.addAfterEachHandler(block.apply("first"));
        suite.addAfterEachHandler(block.apply("second"));
        runner.runChild(new Spec(suite, "spec", Optional.of(() -> {})), new RunNotifier());
        assertEquals(Arrays.asList("first", "second"), calls);
    }


    @Test
    public void itExecutesBeforeEachHandlersBeforeTheSpecIsExecuted() {
        suite.addBeforeEachHandler(block.apply("beforeEach"));
        runner.runChild(new Spec(suite, "spec", Optional.of(block.apply("spec"))), new RunNotifier());
        assertEquals(Arrays.asList("beforeEach", "spec"), calls);
    }


    @Test
    public void itExecutesAfterEachHandlersAfterTheSpecIsExecuted() {
        suite.addAfterEachHandler(block.apply("afterEach"));
        runner.runChild(new Spec(suite, "spec", Optional.of(block.apply("spec"))), new RunNotifier());
        assertEquals(Arrays.asList("spec", "afterEach"), calls);
    }


    @Test
    public void itExecutesOuterBeforeEachHandlersBeforeInnerOnes() {
        suite.addBeforeEachHandler(block.apply("outerBeforeEach"));
        Suite child = new Suite(suite, "child");
        child.addBeforeEachHandler(block.apply("innerBeforeEach"));
        runner.runChild(new Spec(child, "spec", Optional.of(() -> {})), new RunNotifier());
        assertEquals(Arrays.asList("outerBeforeEach", "innerBeforeEach"), calls);
    }


    @Test
    public void itExecutesInnerAfterEachHandlersBeforeOuterOnes() {
        suite.addAfterEachHandler(block.apply("outerBeforeEach"));
        Suite child = new Suite(suite, "child");
        child.addAfterEachHandler(block.apply("innerBeforeEach"));
        runner.runChild(new Spec(child, "spec", Optional.of(() -> {})), new RunNotifier());
        assertEquals(Arrays.asList("innerBeforeEach", "outerBeforeEach"), calls);
    }

}
