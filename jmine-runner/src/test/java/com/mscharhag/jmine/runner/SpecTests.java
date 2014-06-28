package com.mscharhag.jmine.runner;

import com.mscharhag.jmine.runner.suite.Spec;
import com.mscharhag.jmine.runner.suite.Suite;
import org.junit.runner.RunWith;

import static com.mscharhag.jmine.runner.suite.StaticSuiteBuilderSupport.beforeEach;
import static com.mscharhag.jmine.runner.suite.StaticSuiteBuilderSupport.describe;
import static com.mscharhag.jmine.runner.suite.StaticSuiteBuilderSupport.it;
import static org.junit.Assert.assertEquals;

@RunWith(JMineRunner.class)
public class SpecTests {

	private Suite suite;
	private Spec spec;
	private Invokable emptyInvokable = () -> {};

{
	describe("Spec", () -> {

		beforeEach(() -> {
			suite = new Suite(null, "suite");
		});

		describe("when a simple spec is created", () -> {
			beforeEach(() -> {
				spec = new Spec(suite, "test spec", emptyInvokable);
			});

			it("returns the suite", () -> {
				assertEquals(suite, spec.getSuite());
			});

			it("returns the description", () -> {
				assertEquals("test spec", spec.getDescription());
			});

			it("returns the suite description followed by the spec description as full description", () -> {
				assertEquals("suite, it test spec", spec.getFullDescription());
			});
		});

		describe("when the suite has a parent suite", () -> {
			beforeEach(() -> {
				Suite childSuite = new Suite(suite, "child suite");
				spec = new Spec(childSuite, "test spec", emptyInvokable);
			});

			it("returns the description of all parent suites followed by the spec description as full description", () -> {
				assertEquals("suite, child suite, it test spec", spec.getFullDescription());
			});
		});

		describe("when the suite does not return a description", () -> {
			beforeEach(() -> {
				suite = new Suite(null, null);
				spec = new Spec(suite, "test spec", emptyInvokable);
			});

			it("returns only the spec description as full description", () -> {
				assertEquals("it test spec", spec.getFullDescription());
			});
		});
	});

}}
