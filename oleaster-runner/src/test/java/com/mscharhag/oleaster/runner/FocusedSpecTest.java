package com.mscharhag.oleaster.runner;

import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.junit.Assert.*;

@RunWith(OleasterRunner.class)
public class FocusedSpecTest {{
  describe("The focused spec", () -> {

    fit("is focused and will run", () -> {
      assertTrue(true);
    });

    it("is not focused and will not run" , () -> {
      fail("fail");
    });

    fdescribe("focused describe", () -> {
      it("will run", () -> {
        assertTrue(true);
      });
      it("will also run", () -> {
        assertTrue(true);
      });
    });

    fdescribe("another focused describe", () -> {
      fit("is focused and will run", () -> {
        assertTrue(true);
      });

      it("is not focused and will not run", () -> {
        fail("fail");
      });
    });
  });
}}
