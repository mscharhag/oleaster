package com.mscharhag.oleaster.runner;

import java.util.Arrays;
import java.util.Optional;

import com.mscharhag.oleaster.runner.suite.SuiteBuilder;

import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.AssertUtil.*;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.*;

@RunWith(OleasterRunner.class)
public class SuiteBuilderTest {

	private Invokable invokable = () -> {};
	private SuiteBuilder suiteBuilder;
{
	describe("SuiteBuilder", () -> {

		beforeEach(() -> {
			suiteBuilder = new SuiteBuilder();
		});

		it("initialize new SuiteBuilders with empty collections", () -> {
			assertEmptySuiteBuilderCollections(suiteBuilder);
		});

		it("empties existing collections before an evaluation", () -> {
			// fill collections
			suiteBuilder.describe("test suite", invokable);
			suiteBuilder.before(invokable);
			suiteBuilder.beforeEach(invokable);
			suiteBuilder.afterEach(invokable);
			suiteBuilder.after(invokable);
			suiteBuilder.it("test spec", invokable);

			suiteBuilder.beforeEvaluation();
			assertEmptySuiteBuilderCollections(suiteBuilder);
		});

		describe("when a suite is created", () -> {
			beforeEach(() -> {
				suiteBuilder.describe("new suite", invokable);
			});

			it("adds a single suite definition", () -> {
				assertEquals(1, suiteBuilder.getSuiteDefinitions().size());
			});

			it("returns the new suite definition", () -> {
				assertEquals(invokable, suiteBuilder.getSuiteDefinitions().get("new suite"));
			});

			it("is not possible to add another suite with the same name", () -> {
				expect(() -> {
					suiteBuilder.describe("new suite", invokable);
				}).toFailWith(IllegalArgumentException.class);
			});
		});

		describe("when a spec is created", () -> {
			beforeEach(() -> {
				suiteBuilder.it("new spec", invokable);
			});

			it("returns the new spec", () -> {
				assertEquals(Optional.of(invokable), suiteBuilder.getSpecDefinitions().get("new spec"));
			});

			it("is not possible to add another spec with the same name", () -> {
				expect(() -> {
					suiteBuilder.it("new spec", invokable);
				}).toFailWith(IllegalArgumentException.class);
			});
		});

		describe("when a beforeEach handler is added", () -> {
			beforeEach(() -> {
				suiteBuilder.beforeEach(invokable);
			});

			it("returns the newly added handler", () -> {
				assertEquals(Arrays.asList(invokable), suiteBuilder.getBeforeEachHandlers());
			});
		});

		describe("when a before handler is added", () -> {
			beforeEach(() -> {
				suiteBuilder.before(invokable);
			});

			it("returns the newly added handler", () -> {
				assertEquals(Arrays.asList(invokable), suiteBuilder.getBeforeHandlers());
			});
		});

		describe("when a afterEach handler is added", () -> {
			beforeEach(() -> {
				suiteBuilder.afterEach(invokable);
			});

			it("returns the newly added handler", () -> {
				assertEquals(Arrays.asList(invokable), suiteBuilder.getAfterEachHandlers());
			});
		});

		describe("when a after handler is added", () -> {
			beforeEach(() -> {
				suiteBuilder.after(invokable);
			});

			it("returns the newly added handler", () -> {
				assertEquals(Arrays.asList(invokable), suiteBuilder.getAfterHandlers());
			});
		});
	});
}}
