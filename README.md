Oleaster
=====

Oleaster is a Java 8 JUnit runner that allows you to write JUnit tests like you would write [Jasmine](http://jasmine.github.io/) tests.

```java
import static org.junit.Assert.*;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import com.mscharhag.oleaster.runner.OleasterRunner;

@RunWith(OleasterRunner.class)
public class MyTest {{
	describe("A suite", () -> {
		it("contains spec with an assertion", () -> {
			assertEquals(2, 1 + 1);
		});
	});
}}
```

## Setup

So far Oleaster is mainly a prototype and not intended for production use.
If you want to try Oleaster you can use the current development snapshot.

Maven dependency:
```xml
<dependencies>
	<dependency>
		<groupId>com.mscharhag.oleaster</groupId>
		<artifactId>oleaster-runner</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>

	<!-- Oleaster requires JUnit -->
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.11</version>
	</dependency>
</dependencies>
```

Oleaster is currently hosted by Sonatype. So make sure to add the Sonatype repository to your pom.xml
```xml
<repositories>
	<repository>
		<id>sonatype-snapshots</id>
		<url>https://oss.sonatype.org/content/repositories/snapshots</url>
	</repository>
</repositories>
```

Do not forget to set the Java version of your project to Java 8 (for using Lambda expressions).
In Maven you can do this by using the Maven compiler plugin:
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

## Suites and Specs

Suites are used to describe your tests. A suite is created by using the static `StaticRunnerSupport.describe()` method.
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

A Spec is used to test the state of the code. Specs are created using the static `StaticRunnerSupport.it()` method. Like `describe()`,
`it()` takes a `String` and an `Invokable` instance as parameter. The String describes the test while the `Invokable` implements the
actual test.

```java
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
...
it("describes your test", () -> {
	// use assertions to check the expected result
});
```

## Matchers

Oleaster provides its own matching methods you can use to check for the expected test result. Note that you do not have 
to use Oleaster matchers. There is no problem with using the standard JUnit assert methods or libraries like [Hamcrest](https://code.google.com/p/hamcrest/).
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
In order to use Oleaster matchers you need the `oleaster-matcher` artifact:
```xml
<dependency>
	<groupId>com.mscharhag.oleaster</groupId>
	<artifactId>oleaster-matcher</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```
Note that `oleaster-matcher` translates directly to JUnit assertions and does not require the use of the `OleasterRunner` JUnit runner.
So, you can also use oleaster matchers in your standard JUnit tests if you like.

Matcher objects are created by using on of the various static `expect()` methods of `com.mscharhag.oleaster.matcher.Matchers`. 
 
Usage:
```java
// static import, make sure not to forget this
import static com.mscharhag.oleaster.matcher.Matchers.*;

it("shows some matcher examples", () -> {

	// comparing simple primitive values
	expect(40 + 2).toEqual(42); 
	expect(true).toBeTrue();
	
	// comparing objects
	expect(car).toEqual(car); // uses car.equals()
	
	// checking for exceptions
	expect(() -> {
		// code that throws IllegalArgumentException
	}).toFailWith(IllegalArgumentException.class);
});
```

## Examples

Example usage of Oleaster:

```java
import static org.junit.Assert.*;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import com.mscharhag.oleaster.runner.OleasterRunner;
...

@RunWith(OleasterRunner.class)
public class AudioPlayerTests {

	private AudioPlayer player;
	private Track track;

	public AudioPlayerTests() {
		describe("Tests for AudioPlayer", () -> {

			// beforeEach handlers run before a spec is executed
			beforeEach(() -> {
				player = new AudioPlayer();
			});

			// afterEach handlers run after a spec is executed
			afterEach(() -> {
				player.stop();
			});

			// A simple spec
			it("should set the initial player state to stopped", () -> {
				assertEquals(PlayerState.stopped, player.getState());
			});

			// Test suites can be nested
			describe("when a track is played", () -> {
				beforeEach(() -> {
					track = new Track("/foo/bar/baz.mp3");
					player.play(track);
				});

				it("sets the player state to playing", () -> {
					assertEquals(PlayerState.playing, player.getState());
				});

				it("sets the current track", () -> {
					assertEquals(track, player.getCurrentTrack());
				});
			});
		});
	}
}
```

For more examples you can have a look at [Oleaster tests](https://github.com/mscharhag/oleaster/tree/master/oleaster-runner/src/test/java/com/mscharhag/oleaster/runner) which are written in Oleaster itself.

## License

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