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

import com.mscharhag.oleaster.runner.Invokable;
import org.junit.runners.model.Statement;

public class Spec extends Statement {

	private Suite suite;
	private String description;
	private Invokable block;

	public Spec(Suite suite, String description, Invokable block) {
		this.suite = suite;
		this.description = description;
		this.block = block;
	}

	public String getDescription() {
		return this.description;
	}

	public Suite getSuite() {
		return suite;
	}

	public String getFullDescription() {
		String suiteDescription = suite.getFullDescription();
		if (suiteDescription != null) {
			return String.format("%s, it %s", suiteDescription, this.description);
		}
		return String.format("it %s", this.description);
	}

	@Override
	public void evaluate() throws Throwable {
		block.invoke();
	}
}
