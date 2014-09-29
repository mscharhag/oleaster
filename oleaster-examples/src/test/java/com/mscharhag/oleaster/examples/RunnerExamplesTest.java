package com.mscharhag.oleaster.examples;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class RunnerExamplesTest {{

	it("describes your test", () -> {
		// use assertions to check the expected result
	});

	describe("describes your suite", () -> {
		// suite implementation
	});

	describe("a suite", () -> {
		describe("a nested suite", () -> {
			// suite implementation
		});
	});

	describe("beforeEach/afterEach example", () -> {

		beforeEach(() -> {
			// runs before every spec in this suite
		});

		afterEach(() -> {
			// runs after every spec in this suite
		});
	});
}}
