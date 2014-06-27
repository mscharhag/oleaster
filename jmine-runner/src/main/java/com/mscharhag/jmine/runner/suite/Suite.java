package com.mscharhag.jmine.runner.suite;

import com.mscharhag.jmine.runner.Invokable;

import java.util.ArrayList;
import java.util.List;

public class Suite {

	private Suite parent;
	private String description;

	private List<Suite> suites = new ArrayList<>();
	private List<Spec> specs = new ArrayList<>();
	private List<Invokable> beforeEachHandlers = new ArrayList<>();
	private List<Invokable> afterEachHandlers = new ArrayList<>();


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

	public List<Invokable> getAfterEachHandlers() {
		return this.afterEachHandlers;
	}

	public List<Spec> collectSpecs() {
		List<Spec> allSpecs = new ArrayList<>(this.specs);
		this.suites.forEach(suite -> allSpecs.addAll(suite.collectSpecs()));
		return allSpecs;
	}

	public String getDescription() {
		return description;
	}

	public List<Suite> getSuites() {
		return suites;
	}

	public List<Spec> getSpecs() {
		return specs;
	}
}
