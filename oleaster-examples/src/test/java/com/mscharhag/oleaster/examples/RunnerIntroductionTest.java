package com.mscharhag.oleaster.examples;

import org.junit.runner.RunWith;
import com.mscharhag.oleaster.runner.OleasterRunner;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static com.mscharhag.oleaster.matcher.Matchers.*;

/**
 * This test shows a minimal Oleaster test containing a suite and a spec.
 * It is shown in the introduction of the Oleaster documentation.
 */
@RunWith(OleasterRunner.class)
public class RunnerIntroductionTest {{
	describe("A suite", () -> {
		it("contains spec with an expectation", () -> {
			expect(40 + 2).toEqual(42);
		});
	});
}}
