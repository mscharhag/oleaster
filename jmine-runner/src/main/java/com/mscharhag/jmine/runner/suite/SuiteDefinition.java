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
package com.mscharhag.jmine.runner.suite;

import com.mscharhag.jmine.runner.Invokable;

public class SuiteDefinition {

	private Suite parent;
	private String description;
	private Invokable block;


	public SuiteDefinition(Suite parent, String description, Invokable block) {
		this.parent = parent;
		this.description = description;
		this.block = block;
	}

	public Suite getParent() {
		return parent;
	}

	public String getDescription() {
		return description;
	}

	public Invokable getBlock() {
		return block;
	}
}
