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
package com.mscharhag.oleaster.matcher.util;

public class Expectations {

	public static void expectNotNull(Object value, String message) {
		if (value == null) {
			fail(message);
		}
	}


	public static void expectNotNull(Object value, String format, Object... args) {
		expectNotNull(value, String.format(format, args));
	}


	public static void expectTrue(boolean condition, String message) {
		if (!condition) {
			fail(message);
		}
	}


	public static void expectTrue(boolean condition, String format, Object... args) {
		expectTrue(condition, String.format(format, args));
	}


	public static void fail(String message) {
		if (message == null) {
			throw new AssertionError();
		}
		throw new AssertionError(message);
	}
}
