package com.mscharhag.jmine.runner;

import com.mscharhag.jmine.runner.suite.SuiteBuilder;
import com.mscharhag.jmine.runner.suite.Spec;
import com.mscharhag.jmine.runner.suite.Suite;
import com.mscharhag.jmine.runner.suite.SuiteDefinition;
import com.mscharhag.jmine.runner.suite.SuiteDefinitionEvaluator;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static com.mscharhag.jmine.runner.AssertUtil.assertEmptySuiteCollections;
import static com.mscharhag.jmine.runner.suite.StaticSuiteBuilderSupport.*;
import static org.junit.Assert.assertEquals;

@RunWith(JMineRunner.class)
public class SuiteDefinitionEvaluatorTests {

	private SuiteDefinitionEvaluator sde;
	private SuiteBuilder sb;
	private Suite suite, parent;
	private Invokable emptyInvokable = () -> {};

{
	describe("SuiteDefinitionEvaluator", () -> {

		beforeEach(() -> {
			sb = new SuiteBuilder();
			sde = new SuiteDefinitionEvaluator();
		});

		describe("when a suite definition is evaluated", () -> {
			beforeEach(() -> {
				suite = sde.evaluate(new SuiteDefinition(null, "test description", emptyInvokable), sb);
			});

			it("initializes the suite with empty collections", () -> {
				assertEmptySuiteCollections(suite);
			});

			it("passes the description to the suite", () -> {
				assertEquals("test description", suite.getDescription());
			});
		});

		describe("when the evaluated suite definition has a parent suite", () -> {
			beforeEach(() -> {
				parent = new Suite(null, "parent");
				suite = sde.evaluate(new SuiteDefinition(parent, "suite definition", emptyInvokable), sb);
			});

			it("passes the parent to the suite", () -> {
				assertEquals(parent, suite.getParent());
			});
		});

		describe("when the suite definition contains specs", () -> {
			beforeEach(() -> {
				SuiteDefinition definition = new SuiteDefinition(null, "suite", () -> sb.it("spec", emptyInvokable));
				suite = sde.evaluate(definition, sb);
			});

			it("adds the specs to the suite", () -> {
				assertEquals(1, suite.getSpecs().size());
				Spec spec = suite.getSpecs().get(0);
				assertEquals(suite, spec.getSuite());
				assertEquals("spec", spec.getDescription());
			});
		});

		describe("when the suite definition contains beforeEach handlers", () -> {
			beforeEach(() -> {
				SuiteDefinition definition = new SuiteDefinition(null, "test", () -> sb.beforeEach(emptyInvokable));
				suite = sde.evaluate(definition, sb);
			});

			it("adds the beforeEach handler to the suite", () -> {
				assertEquals(Arrays.asList(emptyInvokable), suite.getBeforeEachHandlers());
			});
		});

		describe("when the suite definition contains afterEach handlers", () -> {
			beforeEach(() -> {
				SuiteDefinition definition = new SuiteDefinition(null, "test", () -> sb.afterEach(emptyInvokable));
				suite = sde.evaluate(definition, sb);
			});

			it("adds the afterEach handler to the suite", () -> {
				assertEquals(Arrays.asList(emptyInvokable), suite.getAfterEachHandlers());
			});
		});

		describe("when the suite definition contains a child suite definition", () -> {
			beforeEach(() -> {
				SuiteDefinition suiteDefinition = new SuiteDefinition(null, "parent suite", () -> {
					sb.describe("child suite", emptyInvokable);
				});
				suite = sde.evaluate(suiteDefinition, sb);
			});

			it("adds the child suite to the suite", () -> {
				assertEquals(1, suite.getSuites().size());
				Suite childSuite = suite.getSuites().get(0);
				assertEquals("child suite", childSuite.getDescription());
				assertEmptySuiteCollections(childSuite);
			});

			it("sets the parent of the child suite", () -> {
				Suite childSuite = suite.getSuites().get(0);
				assertEquals(suite, childSuite.getParent());
			});
		});

	});
}}
