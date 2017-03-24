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
import com.mscharhag.oleaster.runner.PendingInvokable;

public class SuiteDefinition {

	private Suite parent;
	private String description;
	private Invokable block;
	private boolean parentIsPending;


	public SuiteDefinition(Suite parent, String description, Invokable block) {
		this(parent, description, block, false);
	}
	public SuiteDefinition(Suite parent, String description, Invokable block, boolean parentIsPending) {
		this.parent = parent;
		this.description = description;
		this.block = block;
		this.parentIsPending = parentIsPending;
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

	public boolean isPending() {
		return block instanceof PendingInvokable || parentIsPending;
	}
}
