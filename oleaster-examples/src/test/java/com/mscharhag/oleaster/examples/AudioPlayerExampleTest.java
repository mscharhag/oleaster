package com.mscharhag.oleaster.examples;

import com.mscharhag.oleaster.examples.AudioPlayer.PlayerState;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static com.mscharhag.oleaster.matcher.Matchers.*;

/**
 * This is an Oleaster example test.
 * These test is shown in the Oleaster Runner documentation page.
 * If this test is updated, the documentation should be updated too.
 */
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

	it("sets the currently played track to null", () -> {
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
