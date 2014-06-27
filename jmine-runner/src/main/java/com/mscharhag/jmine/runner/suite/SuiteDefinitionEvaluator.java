package com.mscharhag.jmine.runner.suite;

public class SuiteDefinitionEvaluator {


	public Suite evaluate(SuiteDefinition suiteDefinition, SuiteBuilder suiteBuilder) {
		this.invokeSuiteDefinition(suiteDefinition, suiteBuilder);
		return this.createSuite(suiteDefinition, suiteBuilder);
	}


	private void invokeSuiteDefinition(SuiteDefinition suiteDefinition, SuiteBuilder suiteBuilder) {
		suiteBuilder.beforeEvaluation();
		try {
			suiteDefinition.getBlock().invoke();
		} catch (Exception e) {
			String message = String.format("An error occurred while evaluating suite '%s'", suiteDefinition.getDescription());
			throw new RuntimeException(message, e);
		} finally {
			suiteBuilder.afterEvaluation();
		}
	}


	private Suite createSuite(SuiteDefinition suiteDefinition, SuiteBuilder suiteBuilder) {
		Suite suite = new Suite(suiteDefinition.getParent(), suiteDefinition.getDescription());

		suite.addBeforeEachHandlers(suiteBuilder.getBeforeEachHandlers());
		suite.addAfterEachHandlers(suiteBuilder.getAfterEachHandlers());

		suiteBuilder.getSpecDefinitions().forEach((description, block) -> {
			suite.addSpec(new Spec(suite, description, block));
		});

		suiteBuilder.getSuiteDefinitions().forEach((description, block) -> {
			SuiteDefinition childSuiteDefinition = new SuiteDefinition(suite, description, block);
			suite.addChildSuite(this.evaluate(childSuiteDefinition, suiteBuilder));
		});

		return suite;
	}
}
