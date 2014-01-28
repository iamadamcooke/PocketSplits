package com.pocketsplits;

public class TimeHelpers {
	
	public static String millisToStringTime(long time) {
		int seconds = (int) (time / 1000);
		int minutes = seconds / 60;
		seconds = seconds % 60;
		int milliseconds = (int) (time % 1000);
		return "" + String.format("%02d", minutes) + ":"
				+ String.format("%02d", seconds) + ":"
				+ String.format("%03d", milliseconds);
	}

}
