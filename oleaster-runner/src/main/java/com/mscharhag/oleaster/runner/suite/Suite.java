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

import java.util.ArrayList;
import java.util.List;

public class Suite {

	private Suite parent;
	private String description;

	private List<Suite> suites = new ArrayList<>();
	private List<Spec> specs = new ArrayList<>();
	private List<Invokable> beforeEachHandlers = new ArrayList<>();
	private List<Invokable> beforeHandlers = new ArrayList<>();
	private List<Invokable> afterEachHandlers = new ArrayList<>();
	private List<Invokable> afterHandlers = new ArrayList<>();


	public Suite(Suite parent, String description) {
		this.parent = parent;
		this.description = description;
	}

	public void addBeforeEachHandler(Invokable block) {
		beforeEachHandlers.add(block);
	}

	public void addAfterEachHandler(Invokable block) {
		afterEachHandlers.add(block);
	}

	public void addBeforeEachHandlers(List<Invokable> calls) {
		this.beforeEachHandlers.addAll(calls);
	}

	public void addBeforeHandler(Invokable block) {
		this.beforeHandlers.add(block);
	}

	public void addBeforeHandlers(List<Invokable> calls) {
		this.beforeHandlers.addAll(calls);
	}

	public void addAfterHandler(Invokable block) {
		this.afterHandlers.add(block);
	}

	public void addAfterHandlers(List<Invokable> calls) {
		this.afterHandlers.addAll(calls);
	}

	public void addAfterEachHandlers(List<Invokable> calls) {
		this.afterEachHandlers.addAll(calls);
	}

	public void addSpec(Spec spec) {
		this.specs.add(spec);
	}

	public Suite getParent() {
		return parent;
	}

	public void addChildSuite(Suite child) {
		this.suites.add(child);
	}

	public List<Invokable> getBeforeEachHandlers() {
		return this.beforeEachHandlers;
	}

	public List<Invokable> getBeforeHandlers() {
		return this.beforeHandlers;
	}

	public List<Invokable> getAfterEachHandlers() {
		return this.afterEachHandlers;
	}

	public List<Invokable> getAfterHandlers() {
		return this.afterHandlers;
	}


	public List<Spec> collectSpecs() {
		List<Spec> allSpecs = new ArrayList<>(this.specs);
		this.suites.forEach(suite -> allSpecs.addAll(suite.collectSpecs()));
		return allSpecs;
	}

	public String getDescription() {
		return description;
	}

	public String getFullDescription() {
		if (this.parent != null) {
			String parentDescription = this.parent.getFullDescription();
			if (parentDescription != null) {
				return String.format("%s, %s", parentDescription, this.description);
			}
		}
		return this.description;
	}

	public List<Suite> getSuites() {
		return suites;
	}

	public List<Spec> getSpecs() {
		return specs;
	}
}
