package com.mscharhag.oleaster.matcher;

import org.junit.Assert;

public class ExceptionMatcher {

	private Exception exception;

	public ExceptionMatcher(CodeBlock block) {
		this.exception = null;
		this.runCodeBlock(block);
	}

	private void runCodeBlock(CodeBlock block) {
		try {
			block.run();
		} catch (Exception e) {
			this.exception = e;
		}
	}

	public <T extends Exception> void toFailWith(Class<T> type) {
		if (this.exception == null) {
			throw new AssertionError("Expected code block to fail with " + type.getName() + " but it did not throw an exception");
		}
		Assert.assertTrue(type.isAssignableFrom(exception.getClass()));
	}

	public static interface CodeBlock {
		public void run() throws Exception;
	}
}
