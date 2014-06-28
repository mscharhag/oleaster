Oleaster
=====

Oleaster is a small JUnit Runner that allows you writing JUnit tests like you would write [Jasmine][1] tests.

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

[1]: http://jasmine.github.io/