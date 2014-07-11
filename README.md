Oleaster
=====

Oleaster is a Java 8 JUnit runner that allows you to write JUnit tests like you would write [Jasmine](http://jasmine.github.io/) tests.

```java
import static org.junit.Assert.*;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

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
```
<dependencies>
	<dependency>
		<groupId>com.mscharhag.oleaster</groupId>
		<artifactId>oleaster-runner</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
</dependencies>
```

Oleaster is currently hosted by Sonatype. So make sure to add the Sonatype repository to your pom.xml
```
<repositories>
	<repository>
		<id>sonatype-snapshots</id>
		<url>https://oss.sonatype.org/content/repositories/snapshots</url>
	</repository>
</repositories>
```

## Suites and Specs

Suites are used to describe your tests. A suite is created by using the static `Oleaster.describe()` method.
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

A Spec is used to test the state of the code. Specs are created using the static `Oleaster.it()` method. Like `describe()`,
`it()` a `String` and an `Invokable` instance as parameter. The String describes the test while the `Invokable` implements the
actual test.

```java
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
...
it("describes your test", () -> {
	// use assertions to check the expected result
});
```

## Examples

Example usage of Oleaster:

```java
import static org.junit.Assert.*;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
...

@RunWith(OleasterRunner.class)
public class AudioPlayerTests {

	private AudioPlayer player;
	private Track track;

	public AudioPlayerTests() {
		describe("Tests for AudioPlayer", () -> {
			beforeEach(() -> { // create a new audio player before each test
				player = new AudioPlayer();
			});

			it("should set the initial player state to stopped", () -> {
				assertEquals(PlayerState.stopped, player.getState());
			});

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