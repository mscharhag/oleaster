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
package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.matcher.util.Arguments;
import com.mscharhag.oleaster.matcher.util.Expectations;

/**
 * Matcher class to validate if a code block throws an exception.
 */
public class ExceptionMatcher {

	private Exception exception;

	/**
	 * Creates a new {@code ExceptionMatcher} that runs the passed {@code CodeBlock}.
	 * @param block the code block that should throw an exception
	 */
	public ExceptionMatcher(CodeBlock block) {
		this.exception = null;
		this.runCodeBlock(block);
	}

	/**
	 * Checks if an exception of type {@code expectedExceptionClass} was thrown.
	 * <p>This method throws an {@code AssertionError} if:
	 * <ul>
	 *     <li>no exception was thrown.</li>
	 *     <li>the thrown exception is not an instance of {@code expectedExceptionClass}</li>
	 * </ul>
	 * @param expectedExceptionClass the expected exception
	 */
	public <T extends Exception> void toThrow(Class<T> expectedExceptionClass) {
		Arguments.ensureNotNull(expectedExceptionClass, "expectedExceptionClass cannot be null");
		String expectedExceptionName = expectedExceptionClass.getName();
		Expectations.expectNotNull(this.exception, "Expected code block to throw %s but it did not throw an exception",
				expectedExceptionName);
		String exceptionName = this.exception.getClass().getName();
		Expectations.expectTrue(expectedExceptionClass.isInstance(this.exception),
				"Expected code block to throw %s but it did throw %s", expectedExceptionName, exceptionName);
	}

	/**
	 * Checks if an exception of type {@code expectedExceptionClass} with message {@code expectedMessage} was thrown.
	 * <p>This method throws an {@code AssertionError} if:
	 * <ul>
	 *     <li>no exception was thrown.</li>
	 *     <li>the thrown exception is not an instance of {@code expectedExceptionClass}</li>
	 *     <li>the message of the thrown exception is not equal {@code expectedMessage}</li>
	 * </ul>
	 * @param expectedExceptionClass the expected exception
	 * @param expectedMessage the expected message
	 */
	public <T extends Exception> void toThrow(Class<T> expectedExceptionClass, String expectedMessage) {
		Arguments.ensureNotNull(expectedMessage, "expectedMessage cannot be null");
		this.toThrow(expectedExceptionClass);
		String exceptionMessage = this.exception.getMessage();
        Expectations.expectTrue(expectedMessage.equals(exceptionMessage),
                "Expected exception message '%s' but was '%s'", expectedMessage, exceptionMessage);
	}

	private void runCodeBlock(CodeBlock block) {
		try {
			block.run();
		} catch (Exception e) {
			this.exception = e;
		}
	}

	/**
	 * {@code CodeBlock} represents the piece of code that should throw an exception.
	 * <p>{@code CodeBlock} is a functional interface that is typically implemented using
	 * Java 8 Lambda expressions. Its single method {@code run()} can throw checked exceptions.
	 */
	public static interface CodeBlock {
		public void run() throws Exception;
	}
}
