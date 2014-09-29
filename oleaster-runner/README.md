Oleaster Runner
=====
Oleaster Runner is a JUnit Runner that allows you to write JUnit tests like you would write Jasmine tests.

````java
import org.junit.runner.RunWith;
import com.mscharhag.oleaster.runner.OleasterRunner;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class RunnerIntroductionTest {{
	describe("A suite", () -> {
		it("contains a spec with an expectation", () -> {
			// test code
		});
	});
}}
```

## Setup
To use `OleasterRunner` you need to add `oleaster-runner.jar` to you classpath. Since `OleasterRunner` is a JUnit
Runner you also need JUnit 4 in your classpath.
If you are using Maven you just have to add the following dependencies to your `pom.xml`:
```xml
<dependencies>
	<dependency>
		<groupId>com.mscharhag.oleaster</groupId>
		<artifactId>oleaster-runner</artifactId>
		<version>0.1.0-SNAPSHOT</version>
	</dependency>

	<!-- Oleaster requires JUnit -->
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.11</version>
	</dependency>
</dependencies>
```
`OleasterRunner` makes heavy use of Java 8 Lambdas, so make sure you configured Java 8 for you project.
If you are using Maven you can configure Java 8 with the `maven-compiler-plugin` plugin:
```xml
<build>
	<plugins>
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.1</version>
			<configuration>
				<source>1.8</source>
				<target>1.8</target>
			</configuration>
		</plugin>
	</plugins>
</build>
```
To improve readability it is recommended to statically import `StaticRunnerSupport.*` in your test classes. This gives
you direct access to Oleaster's static `describe()`, `it()`, `beforeEach()` and `afterEach()` methods:
```java
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
```

## Specs and Suites
Specs and Suites are used to structure tests. 

A Spec is used to test the state of the code. Specs are created using the static `StaticRunnerSupport.it()` method. 
`it()` requires two method parameters: a `String` and an `Invokable` instance. The `String` is used to describe the
purpose of the test. `Invokable` is a single method interface that is typically implemented using a Java 8 lambda 
expression. It implements the actual test.
```java
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
...
it("describes your test", () -> {
	// use assertions to check the expected result
});
```

Suites are used to describe your tests. A suite is created by using the static `StaticRunnerSupport.describe()` method.
Like `it()`, `describe()` takes a `String` and an `Invokable` parameter. The `String` describes the suite.
The `Invokable` implements the suite and (typically) contains specs.

`describe()` takes a `String` and an `Invokable` instance as parameter. The String parameter is used to describe the purpose of your tests.
`Invokable` is a single method interface that is typically implemented using a Java 8 lambda expression. It represents
the piece of code that implements the suite and (typically) contains specs.

```java
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
...
describe("describes your suite", () -> {
	// suite implementation
});
```

Specs and Suites are defined when an instance of the test class is created (e.g. in the constructor of test class).
```java
import org.junit.runner.RunWith;
import com.mscharhag.oleaster.runner.OleasterRunner;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class MyTest {
	public MyTest() {
		describe("A suite", () -> {
			it("contains a spec with an expectation", () -> {
				// test code
			});
		});
	}
}
```
Instead of using the constructor it is also possible to use [initializer blocks](http://docs.oracle.com/javase/tutorial/java/javaOO/initial.html).
The following test does exactly the same as the test shown above (except that imports are skipped).
```java
@RunWith(OleasterRunner.class)
public class MyTest {{
	describe("A suite", () -> {
		it("contains a spec with an expectation", () -> {
			// test code
		});
	});
}}
```
Here the suite is defined in an initializer block (note the double braces).

Suites are optional, so this works too:
```java
@RunWith(OleasterRunner.class)
public class MyTest {{
	it("contains a spec with an expectation", () -> {
		// test code
	});
}}
```

Suites can be nested:
```java
describe("a suite", () -> {
	describe("a nested suite", () -> {
		// suite implementation
	});
});
```

## Running code before/after specs
With `beforeEach()` and `afterEach()` it is possible to run code before and after a spec is executed:
```java
describe("beforeEach/afterEach example", () -> {
	beforeEach(() -> {
		// runs before every spec in this suite
	});
	
	afterEach(() -> {
		// runs after every spec in this suite  
	});
});
```
If `beforeEach()` or `afterEach()` is used inside nested suites, `beforeEach()`/`afterEach()` blocks of the outer suite
will run first.

## Assertions

Validating the result of a test can be done in various ways.
Oleaster Matchers provide Jasmine-like Matchers for checking expected test results. However it is also possible to use
standard JUnit assertions or additional libraries like [Hamcrest](https://github.com/hamcrest/JavaHamcrest).
```java
it("shows different assertion styles", () -> {
	int value = 42;
	
	// standard JUnit
	assertEquals(42, value);
	 
	// Hamcrest
	assertThat(value, equalTo(42));
	 
	// Jasmine / Oleaster
	expect(value).toEqual(42); 
});
```
Have a look at the [Oleaster Matchers](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/README.md)
documentation for more information.

## Examples
The following code shows an example of a more complete Oleaster test:

```java
@RunWith(OleasterRunner.class)
public class AudioPlayerExampleTest {
	private AudioPlayer player;
{
	beforeEach(() -> {
		// create a new AudioPlayer instance before each test
		player = new AudioPlayer();
	});

	it("sets the initial player state to stopped", () -> {
		expect(player.getState()).toEqual(PlayerState.stopped);
	});

	it("initializes the currently played track with null", () -> {
		expect(player.getCurrentlyPlayedTrack()).toBeNull();
	});

	describe("when a track is played", () -> {
		beforeEach(() -> {
			// play a track; this is the common precondition for all specs in this suite
			player.play(new Track("Run to the hills"));
		});

		it("updates the player state to playing", () -> {
			expect(player.getState()).toEqual(PlayerState.playing);
		});

		it("updates the currently played track", () -> {
			Track expectedTrack = new Track("Run to the hills");
			expect(player.getCurrentlyPlayedTrack()).toEqual(expectedTrack);
		});
	});
}}
```
You can find the source for this example in the `oleaster-examples` module (see: [AudioPlayerExampleTest.java](https://github.com/mscharhag/oleaster/blob/master/oleaster-examples/src/test/java/com/mscharhag/oleaster/examples/AudioPlayerExampleTest.java)).

For more examples you can have a look at Oleaster tests which are written with Oleaster.
See:

* [Oleaster Runner tests](https://github.com/mscharhag/oleaster/tree/master/oleaster-runner/src/test/java/com/mscharhag/oleaster/runner)
* [Oleaster Matcher tests](https://github.com/mscharhag/oleaster/tree/master/oleaster-matcher/src/test/java/com/mscharhag/oleaster/matcher/matchers)

## Licence

This software is licensed under the Apache 2 license, quoted below.

Copyright 2014 Michael Scharhag

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.