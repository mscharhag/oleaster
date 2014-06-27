package com.mscharhag.jmine.runner;

import static com.mscharhag.jmine.runner.suite.StaticSuiteBuilderSupport.*;

import com.mscharhag.jmine.runner.suite.Spec;
import com.mscharhag.jmine.runner.suite.Suite;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;

import static org.junit.Assert.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(JMineRunner.class)
public class JMineRunnerTests {

	private static List<String> calls;
	private static Function<String, Invokable> block = (String name) -> () -> { calls.add(name); };

	private JMineRunner runner;
	private Suite suite;

	public static class TestClass {{
		describe("outer describe", () -> {
			beforeEach(block.apply("outer before each"));
			describe("inner describe", () -> {
				it("inner it", block.apply("inner it"));
			});
			it("outer it", block.apply("outer it"));
		});
	}}

{
	describe("JMineRunner tests", () -> {

		beforeEach(() -> {
			calls = new ArrayList<>();
			runner = new JMineRunner(TestClass.class);
		});

		describe("when getChildren() is called", () -> {
			it("should return a list containing all specs from the passed test class", () -> {
				List<Spec> specs = runner.getChildren();
				List specNames = specs.stream().map(Spec::getDescription).collect(Collectors.toList());
				assertEquals(Arrays.asList("outer it", "inner it"), specNames);
			});
		});

		describe("when a spec is executed", () -> {

			beforeEach(() -> {
				suite = new Suite(null, "suite");
			});

			it("should execute beforeEach() callbacks before the spec is executed", () -> {
				suite.addBeforeEachHandler(block.apply("before"));
				Spec spec = new Spec(suite, "spec", block.apply("spec"));
				runner.runChild(spec, new RunNotifier());
				assertEquals(Arrays.asList("before", "spec"), calls);
			});

			it("should execute afterEach() callbacks after the spec is executed", () -> {
				suite.addAfterEachHandler(block.apply("after"));
				Spec spec = new Spec(suite, "spec", block.apply("spec"));
				runner.runChild(spec, new RunNotifier());
				assertEquals(Arrays.asList("spec", "after"), calls);
			});

			it("should execute outer beforeEach() calls before inner ones", () -> {
				suite.addBeforeEachHandler(block.apply("outer"));
				Suite child = new Suite(suite, "child");
				child.addBeforeEachHandler(block.apply("inner"));
				Spec spec = new Spec(child, "spec", () -> {});
				runner.runChild(spec, new RunNotifier());
				assertEquals(Arrays.asList("outer", "inner"), calls);
			});

			it("should execute inner afterEach() calls before outer ones", () -> {
				suite.addAfterEachHandler(block.apply("outer"));
				Suite child = new Suite(suite, "child");
				child.addAfterEachHandler(block.apply("inner"));
				Spec spec = new Spec(child, "spec", () -> {});
				runner.runChild(spec, new RunNotifier());
				assertEquals(Arrays.asList("inner", "outer"), calls);
			});
		});
	});

}}
