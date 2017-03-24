package com.mscharhag.oleaster.runner;


import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static junit.framework.TestCase.*;

@RunWith(OleasterRunner.class)
public class PendingSpecTest {{
  describe("PendingSpecs", () -> {
    describe("with a nested describe", () -> {
      it("should be executed", () -> {
        assertTrue(true);
      });

      xit("should not be executed", () -> {
        fail("Pending it (xit) should not be executed!");
      });

      describe("a nested describe should be executed", () -> {
        it("should be fine", () -> {
          assertTrue(true);
        });
      });

      xdescribe("a pending describe should not be executed", () -> {
        it("should not be executed", () -> {
          fail("'it' in 'xdescribe' should not be executed!");
        });

        describe("a describe with a xdescribe parent", () -> {
          it("should not be executed", () -> {
            fail("'it' in 'describe' with 'xdescribe' parent should not be executed!");
          });

          describe("a describe with a xdescribe grandparent", () -> {
            it("should not be executed", () -> {
              fail("'it' in 'describe' with 'xdescribe' grandparent should not be executed!");
            });
          });
        });
      });
    });

    it("should be executed", () -> {
      assertTrue(true);
    });

    xit("should not be executed", () -> {
      fail("Pending it (xit) should not be executed!");
    });

    it("another pending style of test");
  });
}}
