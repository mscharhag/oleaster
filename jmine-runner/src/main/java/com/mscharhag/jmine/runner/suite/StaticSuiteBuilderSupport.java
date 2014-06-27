package com.mscharhag.jmine.runner.suite;

import com.mscharhag.jmine.runner.Invokable;

public class StaticSuiteBuilderSupport {

	private static SuiteBuilder suiteBuilder;

	static void setSuiteBuilder(SuiteBuilder sb) {
		suiteBuilder = sb;
	}

	public static void describe(String text, Invokable block) {
		suiteBuilder.describe(text, block);
	}

	public static void it(String text, Invokable block) {
		suiteBuilder.it(text, block);
	}

	public static void beforeEach(Invokable block) {
		suiteBuilder.beforeEach(block);
	}

	public static void afterEach(Invokable block) {
		suiteBuilder.afterEach(block);
	}
}
