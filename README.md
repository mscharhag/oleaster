Oleaster
=====

Oleaster is a small JUnit Runner that allows you writing JUnit tests like you would write [Jasmine][1] tests.

Example usage:

```java
import static org.junit.Assert.*;
import static com.mscharhag.oleaster.runner.suite.StaticSuiteBuilderSupport.*;
...

@RunWith(OleasterRunner.class)
public class AudioPlayerTests {
	private AudioPlayer player;
	private Track track;
{
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
}}
```

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
[1]: http://jasmine.github.io/