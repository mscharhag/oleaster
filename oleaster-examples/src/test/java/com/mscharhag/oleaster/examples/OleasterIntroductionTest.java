package com.mscharhag.oleaster.examples;

import org.junit.runner.RunWith;
import com.mscharhag.oleaster.runner.OleasterRunner;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static com.mscharhag.oleaster.matcher.Matchers.*;

/**
 * This test shows a minimal Oleaster test containing a suite and a spec.
 * It is shown in the introduction of the Oleaster documentation.
 * If changes in this file are made, the documentation needs be updated.
 */
@RunWith(OleasterRunner.class)
public class OleasterIntroductionTest {{
	describe("A suite", () -> {
		it("contains a spec with an expectation", () -> {
			expect(40 + 2).toEqual(42);
		});
	});
}}
