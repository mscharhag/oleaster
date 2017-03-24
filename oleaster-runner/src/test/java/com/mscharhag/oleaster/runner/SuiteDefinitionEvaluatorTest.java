package com.mscharhag.oleaster.runner;

import com.mscharhag.oleaster.runner.suite.SuiteBuilder;
import com.mscharhag.oleaster.runner.suite.Spec;
import com.mscharhag.oleaster.runner.suite.Suite;
import com.mscharhag.oleaster.runner.suite.SuiteDefinition;
import com.mscharhag.oleaster.runner.suite.SuiteDefinitionEvaluator;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Optional;

import static com.mscharhag.oleaster.runner.AssertUtil.assertEmptySuiteCollections;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.*;

@RunWith(OleasterRunner.class)
public class SuiteDefinitionEvaluatorTest {

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

		describe("when a suite is pending", () -> {
			final String DESC_ROOT = "root level";
			final String DESC_ONE = "one";
			final String DESC_TWO = "two";
			final String DESC_TWO_ONE = "two > one";
			beforeEach(() -> {
				suite = sde.evaluate(new SuiteDefinition(null, DESC_ROOT, () -> {
					sb.xdescribe(DESC_ONE, () -> {
						sb.it("hello", () -> {
							fail("I should be removed");
						});
					});

					sb.xdescribe(DESC_TWO, () -> {
						sb.describe(DESC_TWO_ONE, () -> {
							sb.it("hello", () -> {
								fail("I should be removed");
							});
						});
					});
				}), sb);
			});

			it("should remove the it block of the it in DESC_ONE", () -> {
				final Suite descOneSuite = suite.getSuites().get(0);
				assertEquals(DESC_ONE, descOneSuite.getDescription());
				assertEquals(Optional.empty(), descOneSuite.getSpecs().get(0).getBlock());
			});

			it("should remove the it block of the it which is deeper nested in DESC_TWO", () -> {
				final Suite descTwoSuite = suite.getSuites().get(1);
				assertEquals(DESC_TWO, descTwoSuite.getDescription());

				final Suite deskTwoOneSuite = descTwoSuite.getSuites().get(0);
				assertEquals(DESC_TWO_ONE, deskTwoOneSuite.getDescription());

				assertEquals(Optional.empty(), deskTwoOneSuite.getSpecs().get(0).getBlock());
			});
		});

	});
}}
