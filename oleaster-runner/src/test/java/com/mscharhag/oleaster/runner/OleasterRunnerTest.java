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

        it("adds the filtered children to the suite", () -> {
            int numberOfChildren = runner.getDescription().getChildren().size();
            assertEquals(2, numberOfChildren);
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
	});

}}
