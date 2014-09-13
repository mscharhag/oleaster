package com.mscharhag.oleaster.matcher;

import java.util.concurrent.Callable;

public class ExceptionMatcher {

	private CodeBlock block;

	public ExceptionMatcher(CodeBlock block) {
		this.block = block;
	}

	public static interface CodeBlock {
		public void run() throws Exception;
	}
}
