package com.mscharhag.oleaster.runner;

import com.mscharhag.oleaster.runner.suite.Suite;
import com.mscharhag.oleaster.runner.suite.SuiteBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AssertUtil {

	public static void assertEmptySuiteBuilderCollections(SuiteBuilder sb) {
		assertEquals(0, sb.getSuiteDefinitions().size());
		assertEquals(0, sb.getAfterEachHandlers().size());
		assertEquals(0, sb.getBeforeEachHandlers().size());
		assertEquals(0, sb.getSpecDefinitions().size());
	}


	public static void assertEmptySuiteCollections(Suite suite) {
		assertEquals(0, suite.getSpecs().size());
		assertEquals(0, suite.getBeforeEachHandlers().size());
		assertEquals(0, suite.getAfterEachHandlers().size());
		assertEquals(0, suite.getSuites().size());
	}


	public static ExceptionMatcher expect(Invokable block) {
		return new ExceptionMatcher(block);
	}


	public static class ExceptionMatcher {
		private Invokable block;

		private ExceptionMatcher(Invokable block) {
			this.block = block;
		}

		public void toFailWith(Class<? extends Exception> exceptionClass) {
			Exception ex = null;
			try {
				block.invoke();
			} catch (Exception e) {
				ex = e;
			}
			if (ex == null) {
				fail("Expected exception of type " + exceptionClass.getName() + " but no exception was thrown");
			}
			if (!ex.getClass().isAssignableFrom(exceptionClass) || !exceptionClass.isAssignableFrom(ex.getClass())) {
				fail("Expected exception of type " + exceptionClass.getName() + " but got " + ex.getClass().getName());
			}
		}
	}
}
