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
package com.mscharhag.oleaster.runner;

/**
 * An Invokable represents a block of code in Oleaster.
 * Common Oleaster functions like {@code describe()} or {@code beforeEach()}
 * take an Invokable as parameter. These Invokable instances are then evaluated by Oleaster
 * to build a set of test suites.
 *
 * <p>Invokable is typically implemented using a Lambda expression.</p>
 *
 * Invokable is similar to {@code Runnable}. The only difference is that Invokable allows you to
 * throw checked exceptions.
 */
public interface Invokable {

	void invoke() throws Exception;

}
