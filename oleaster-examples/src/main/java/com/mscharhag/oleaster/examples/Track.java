package com.mscharhag.oleaster.examples;

public class Track {

	private String title;

	public Track(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		return title.equals(((Track)o).title);
	}

	@Override
	public int hashCode() {
		return title.hashCode();
	}
}
