/*
* Copyright 2014 Michael Scharhag
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.mscharhag.oleaster.runner.suite;

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

		suite.addBeforeHandlers(suiteBuilder.getBeforeHandlers());
		suite.addBeforeEachHandlers(suiteBuilder.getBeforeEachHandlers());
		suite.addAfterEachHandlers(suiteBuilder.getAfterEachHandlers());
		suite.addAfterHandlers(suiteBuilder.getAfterHandlers());

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
