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
package com.mscharhag.oleaster.matcher;

import com.mscharhag.oleaster.matcher.matchers.*;

public class Matchers {

	public static IntegerNumberMatcher expect(long value) {
		return new IntegerNumberMatcher(value);
	}

	public static IntegerNumberMatcher expect(int value) {
		return new IntegerNumberMatcher(value);
	}

	public static IntegerNumberMatcher expect(short value) {
		return new IntegerNumberMatcher(value);
	}

	public static IntegerNumberMatcher expect(byte value) {
		return new IntegerNumberMatcher(value);
	}

	public static FloatingPointNumberMatcher expect(double value) {
		return new FloatingPointNumberMatcher(value);
	}

	public static FloatingPointNumberMatcher expect(float value) {
		return new FloatingPointNumberMatcher(value);
	}

	public static ExceptionMatcher expect(ExceptionMatcher.CodeBlock block) {
		return new ExceptionMatcher(block);
	}

	public static BooleanMatcher expect(boolean value) {
		return new BooleanMatcher(value);
	}

	public static StringMatcher expect(String value) {
		return new StringMatcher(value);
	}

	public static ObjectMatcher<Object> expect(Object value) {
		return new ObjectMatcher<>(value);
	}
}
