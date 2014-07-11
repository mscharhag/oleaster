package com.mscharhag.oleaster.runner;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

import com.mscharhag.oleaster.runner.suite.Spec;
import com.mscharhag.oleaster.runner.suite.Suite;
import com.mscharhag.oleaster.runner.suite.SuiteBuilder;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;

import static org.junit.Assert.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(OleasterRunner.class)
public class OleasterRunnerTest {

	private static List<String> calls;
	private static Function<String, Invokable> block = (String name) -> () -> { calls.add(name); };

	private OleasterRunner runner;
	private Suite suite;
	private List<Spec> specs;

	public static class TestClass {{
		describe("outer describe", () -> {
			describe("inner describe", () -> {
				it("inner it", block.apply("inner it"));
			});
			it("outer it", block.apply("outer it"));
		});
	}}


	public static class OleasterTestImplementingTestClass implements OleasterTest {

		public static SuiteBuilder suiteBuilder;

		@Override
		public void buildTestSuite(SuiteBuilder sb) {
			suiteBuilder = sb;
		}
	}

{
	describe("OleasterRunner", () -> {

		beforeEach(() -> {
			calls = new ArrayList<>();
			runner = new OleasterRunner(TestClass.class);
		});

		describe("when specs are obtained from the test class using getChildren()", () -> {

			beforeEach(() -> {
				specs = runner.getChildren();
			});

			it("returns a list that contains all specs", () -> {
				List specNames = specs.stream().map(Spec::getDescription).collect(Collectors.toList());
				assertEquals(Arrays.asList("outer it", "inner it"), specNames);
			});

			describe("when the test class implements OleasterTest", () -> {
				beforeEach(() -> {
					OleasterTestImplementingTestClass.suiteBuilder = null;
					runner = new OleasterRunner(OleasterTestImplementingTestClass.class);
					runner.getChildren();
				});

				it("calls buildTestSuite() and passes a SuiteBuilder instance", () -> {
					assertNotNull(OleasterTestImplementingTestClass.suiteBuilder);
				});
			});
		});

		describe("when a spec is executed", () -> {
			beforeEach(() -> {
				suite = new Suite(null, "suite");
			});

			it("executes beforeEach handlers before the spec is executed", () -> {
				suite.addBeforeEachHandler(block.apply("before"));
				runner.runChild(new Spec(suite, "spec", block.apply("spec")), new RunNotifier());
				assertEquals(Arrays.asList("before", "spec"), calls);
			});

			it("executes afterEach handlers after the spec is executed", () -> {
				suite.addAfterEachHandler(block.apply("after"));
				runner.runChild(new Spec(suite, "spec", block.apply("spec")), new RunNotifier());
				assertEquals(Arrays.asList("spec", "after"), calls);
			});

			it("executes outer beforeEach handlers before inner ones", () -> {
				suite.addBeforeEachHandler(block.apply("outer"));
				Suite child = new Suite(suite, "child");
				child.addBeforeEachHandler(block.apply("inner"));
				runner.runChild(new Spec(child, "spec", () -> {
				}), new RunNotifier());
				assertEquals(Arrays.asList("outer", "inner"), calls);
			});

			it("executes inner afterEach handlers calls before outer ones", () -> {
				suite.addAfterEachHandler(block.apply("outer"));
				Suite child = new Suite(suite, "child");
				child.addAfterEachHandler(block.apply("inner"));
				runner.runChild(new Spec(child, "spec", () -> {
				}), new RunNotifier());
				assertEquals(Arrays.asList("inner", "outer"), calls);
			});
		});
	});

}}
