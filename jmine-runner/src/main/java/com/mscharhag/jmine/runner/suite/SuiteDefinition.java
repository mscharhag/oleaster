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
