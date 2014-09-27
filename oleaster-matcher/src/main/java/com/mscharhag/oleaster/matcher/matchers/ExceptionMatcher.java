package com.mscharhag.oleaster.matcher.matchers;

import org.junit.Assert;

public class ExceptionMatcher {

	private Exception exception;

	public ExceptionMatcher(CodeBlock block) {
		this.exception = null;
		this.runCodeBlock(block);
	}

	public <T extends Exception> void toFailWith(Class<T> expectedExceptionClass) {
		if (expectedExceptionClass == null) {
			throw new IllegalArgumentException("expectedException cannot be null");
		}
		failIfNoExceptionWasThrown(expectedExceptionClass);
		Assert.assertTrue(expectedExceptionClass.isAssignableFrom(exception.getClass()));
	}

	public <T extends Exception> void toFailWith(Class<T> expectedExceptionClass, String expectedMessage) {
		this.toFailWith(expectedExceptionClass);
		Assert.assertEquals(expectedMessage, this.exception.getMessage());
	}

	private void runCodeBlock(CodeBlock block) {
		try {
			block.run();
		} catch (Exception e) {
			this.exception = e;
		}
	}

	private <T extends Exception> void failIfNoExceptionWasThrown(Class<T> expectedExceptionClass) {
		if (this.exception == null) {
			throw new AssertionError("Expected code block to fail with " + expectedExceptionClass.getName()
					+ " but it did not throw an exception");
		}
	}

	public static interface CodeBlock {
		public void run() throws Exception;
	}
}
