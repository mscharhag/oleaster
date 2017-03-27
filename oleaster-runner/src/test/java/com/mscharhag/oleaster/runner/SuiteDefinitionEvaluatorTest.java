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
			StaticRunnerSupport.setSuiteBuilder(sb);
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
					xdescribe(DESC_ONE, () -> {
						it("hello", () -> {
							fail("I should be removed");
						});
					});

					xdescribe(DESC_TWO, () -> {
						describe(DESC_TWO_ONE, () -> {
							it("hello", () -> {
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

		describe("when a suite is focused", () -> {
			final String DESC_ONE = "desc-one";
			final String DESC_TWO = "desc-two";
			final String IT_ONE = "it-one";
			final String IT_TWO = "it-two";

			beforeEach(() -> {
				suite = sde.evaluate(new SuiteDefinition(null, "root", () -> {
					describe(DESC_ONE, () -> {
						it(IT_ONE, () -> fail("should not be found."));
					});

					fdescribe(DESC_TWO, () -> {
						it(IT_TWO, () -> assertTrue(true));
					});
				}), sb);
			});

			it("should not find DESC_ONE nor IT_ONE", () -> {
				assertEquals(1, suite.getSuites().size());
				assertFalse(DESC_ONE.equals(suite.getSuites().get(0).getDescription()));
			});

			it("should find only DESC_TWO",() -> {
				assertTrue(DESC_TWO.equals(suite.getSuites().get(0).getDescription()));
			});
		});

		describe("with a more complex focused and pending suite collection", () -> {
			final String DESC_ONE = "d1";
			final String IT_ONE = "d1-i1";
			final String DESC_TWO = "d2";
			final String IT_TWO = "d2-i1";
			final String DESC_THREE = "d3";
			final String IT_THREE = "d3-i1";
			final String IT_FOUR = "d3-i2";
			final String IT_FIVE = "d3-i3";
			beforeEach(() -> {
				suite = sde.evaluate(new SuiteDefinition(null, "ROOT", () -> {
					fdescribe(DESC_ONE, () -> {
						it(IT_ONE, () -> assertTrue(true));
					});

					describe(DESC_TWO, () -> {
						it("does not run", () -> fail("does not run"));
						fit(IT_TWO, () -> assertTrue(true));
					});

					fdescribe(DESC_THREE, () -> {
						it(IT_THREE, () -> assertTrue(true));
						xit(IT_FOUR, () -> fail("does not run"));
						it(IT_FIVE);
					});
				}), sb);
			});

			it("should run it's in the first focused describe", () -> {
				assertEquals(DESC_ONE, suite.getSuites().get(0).getDescription());
				assertEquals(1, suite.getSuites().get(0).getSpecs().size());
				assertEquals(IT_ONE, suite.getSuites().get(0).getSpecs().get(0).getDescription());
			});

			it("should not run DESC_TWO", () -> {
				assertEquals(2, suite.getSuites().size());
				suite.getSuites().forEach(s -> assertNotEquals(DESC_TWO, s.getDescription()));
			});

			it("should not run it xit's in DESC_THREE", () -> {
				final Suite d3 = suite.getSuites().get(1);
				assertEquals(DESC_THREE, d3.getDescription());
				assertEquals(3, d3.getSpecs().size());

				assertEquals(IT_THREE, d3.getSpecs().get(0).getDescription());
				assertTrue(d3.getSpecs().get(0).getBlock().isPresent());

				assertEquals(IT_FOUR, d3.getSpecs().get(1).getDescription());
				assertFalse(d3.getSpecs().get(1).getBlock().isPresent());
			});

			it("should not run it's without bodies", () -> {
				final Suite d3 = suite.getSuites().get(1);

				assertEquals(IT_FIVE, d3.getSpecs().get(2).getDescription());
				assertFalse(d3.getSpecs().get(2).getBlock().isPresent());
			});
		});
	});
}}
