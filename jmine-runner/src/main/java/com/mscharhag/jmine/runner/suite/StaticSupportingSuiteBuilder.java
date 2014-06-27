package com.mscharhag.jmine.runner.suite;

public class StaticSupportingSuiteBuilder extends SuiteBuilder {

	@Override
	public void beforeEvaluation() {
		super.beforeEvaluation();
		StaticSuiteBuilderSupport.setSuiteBuilder(this);
	}

	@Override
	public void afterEvaluation() {
		super.afterEvaluation();
		StaticSuiteBuilderSupport.setSuiteBuilder(null);
	}
}
