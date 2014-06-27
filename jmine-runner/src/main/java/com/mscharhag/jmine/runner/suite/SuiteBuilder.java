package com.mscharhag.jmine.runner.suite;


import com.mscharhag.jmine.runner.Invokable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SuiteBuilder {

	private Map<String, Invokable> suiteDefinitions;
	private Map<String, Invokable> specDefinitions;
	private List<Invokable> beforeEachHandlers;
	private List<Invokable> afterEachHandlers;

	public SuiteBuilder() {
		this.prepare();
	}

	private void prepare() {
		this.suiteDefinitions = new LinkedHashMap<>();
		this.specDefinitions = new LinkedHashMap<>();
		this.beforeEachHandlers = new ArrayList<>();
		this.afterEachHandlers = new ArrayList<>();
	}

	public void beforeEvaluation() {
		this.prepare();
	}

	public void afterEvaluation() {

	}

	public void describe(String description, Invokable definition) {
		if (this.suiteDefinitions.containsKey(description)) {
			throw new IllegalArgumentException(String.format("Suite with description '%s' does already exist", description));
		}
		this.suiteDefinitions.put(description, definition);
	}

	public void it(String description, Invokable definition) {
		if (this.specDefinitions.containsKey(description)) {
			throw new IllegalArgumentException(String.format("Spec with description '%s' does already exist", description));
		}
		this.specDefinitions.put(description, definition);
	}

	public void beforeEach(Invokable block) {
		this.beforeEachHandlers.add(block);
	}

	public void afterEach(Invokable block) {
		this.afterEachHandlers.add(block);
	}

	public Map<String, Invokable> getSuiteDefinitions() {
		return suiteDefinitions;
	}

	public Map<String, Invokable> getSpecDefinitions() {
		return specDefinitions;
	}

	public List<Invokable> getBeforeEachHandlers() {
		return beforeEachHandlers;
	}

	public List<Invokable> getAfterEachHandlers() {
		return afterEachHandlers;
	}
}
