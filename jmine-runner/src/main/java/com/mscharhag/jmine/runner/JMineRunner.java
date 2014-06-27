package com.mscharhag.jmine.runner;

import com.mscharhag.jmine.runner.suite.StaticSupportingSuiteBuilder;
import com.mscharhag.jmine.runner.suite.SuiteBuilder;
import com.mscharhag.jmine.runner.suite.Spec;
import com.mscharhag.jmine.runner.suite.Suite;
import com.mscharhag.jmine.runner.suite.SuiteDefinition;
import com.mscharhag.jmine.runner.suite.SuiteDefinitionEvaluator;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JMineRunner extends ParentRunner<Spec> {

	public JMineRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
	}


	@Override
	protected List<Spec> getChildren() {
		List<Spec> allSpecs = new ArrayList<>();
		try {
			SuiteBuilder suiteBuilder = new StaticSupportingSuiteBuilder();

			SuiteDefinition def = new SuiteDefinition(null, "base", () -> {
				try {
					Object obj = getTestClass().getJavaClass().newInstance();
					if (obj instanceof JMineTest) {
						((JMineTest) obj).buildTestSuite(suiteBuilder);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			SuiteDefinitionEvaluator evaluator = new SuiteDefinitionEvaluator();
			Suite suite = evaluator.evaluate(def, suiteBuilder);

			allSpecs.addAll(suite.collectSpecs());

			System.out.println("found " + allSpecs.size() + " specs");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allSpecs;
	}

	private void runBeforeEachCallbacks(Spec spec) {
		List<Invokable> callbacks = new ArrayList<>();
		Suite parent = spec.getSuite();
		while (parent != null) {
			callbacks.addAll(parent.getBeforeEachHandlers());
			parent = parent.getParent();
		}
		Collections.reverse(callbacks);
		callbacks.forEach(callback -> {
			try {
				callback.invoke();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void runAfterEachCallbacks(Spec spec) {
		// TODO cleanup duplication
		List<Invokable> callbacks = new ArrayList<>();
		Suite parent = spec.getSuite();
		while (parent != null) {
			callbacks.addAll(parent.getAfterEachHandlers());
			parent = parent.getParent();
		}
//		Collections.reverse(callbacks);
		callbacks.forEach(callback -> {
			try {
				callback.invoke();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	protected void runChild(Spec spec, RunNotifier notifier) {
		Description description = describeChild(spec);
		runBeforeEachCallbacks(spec);
		runLeaf(spec, description, notifier);
		runAfterEachCallbacks(spec);
//		System.out.println("runChild finished: " + spec.getDescription());
	}

	@Override
	protected Description describeChild(Spec child) {
//		System.out.println("description: " + child.getDescription());
		return Description.createTestDescription(this.getTestClass().getJavaClass(), child.getDescription());
	}

	@Override
	public Description getDescription() {
		return Description.createSuiteDescription(this.getTestClass().getJavaClass().getName());
	}
}
