package com.mscharhag.oleaster.runner;

import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.suite.StaticSuiteBuilderSupport.beforeEach;
import static com.mscharhag.oleaster.runner.suite.StaticSuiteBuilderSupport.describe;
import static com.mscharhag.oleaster.runner.suite.StaticSuiteBuilderSupport.it;
import static org.junit.Assert.assertEquals;

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
}



	private static class AudioPlayer {
		private Track t;
		public void play(Track t) {
			this.t = t;
		}
		public PlayerState getState() {
			return PlayerState.playing;
		}
		public Track getCurrentTrack() {
			return t;
		}
	}

	private static class Track {
		public Track(String str) {

		}
	}

	private enum PlayerState {
		playing, stopped
	}
}
