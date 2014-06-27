package com.mscharhag.jmine.runner.suite;

import com.mscharhag.jmine.runner.Invokable;
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
		return suite.getDescription() + ", " + this.description;
	}

	@Override
	public void evaluate() throws Throwable {
		block.invoke();
	}
}
