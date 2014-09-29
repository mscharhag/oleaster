package com.mscharhag.oleaster.examples;

import com.mscharhag.oleaster.examples.Track;

public class AudioPlayer {

	private PlayerState state;
	private Track currentlyPlayedTrack;

	public AudioPlayer() {
		this.state = PlayerState.stopped;
		this.currentlyPlayedTrack = null;
	}

	public void play(Track track) {
		this.state = PlayerState.playing;
		this.currentlyPlayedTrack = track;
	}

	public PlayerState getState() {
		return this.state;
	}

	public Track getCurrentlyPlayedTrack() {
		return this.currentlyPlayedTrack;
	}

	public static enum PlayerState {
		stopped, playing
	}
}
