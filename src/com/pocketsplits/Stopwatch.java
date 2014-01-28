package com.pocketsplits;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

public class Stopwatch implements Runnable{

	private long startTime;
	private long timeInMillis = 0L;
	private long timeSwap = 0L;
	private long finalTime = 0L;
	private boolean startTimeSet = false;
	private String currentTime = "00:00:00";
	private TextView textTimer;
	private Handler handler;
	
	public Stopwatch(Handler handler, TextView textTimer) {
		this.handler = handler;
		this.textTimer = textTimer;
		
		
	}
	
	@Override
	public void run() {
		if(!startTimeSet) {
			startTime = SystemClock.uptimeMillis();
			startTimeSet = true;
		}
		timeInMillis = SystemClock.uptimeMillis() - startTime;
		finalTime = timeSwap + timeInMillis;

		int seconds = (int) (finalTime / 1000);
		int minutes = seconds / 60;
		seconds = seconds % 60;
		int milliseconds = (int) (finalTime % 1000);
		textTimer.setText("" + String.format("%02d", minutes) + ":"
				+ String.format("%02d", seconds) + ":"
				+ String.format("%03d", milliseconds));
		handler.postDelayed(this, 0);
		
	}
	
	public String getCurrentTime() {
		return currentTime;
	}
	
	public long getTimeSwap() {
		return timeSwap;
	}
	
	public void setTimeSwap(long timeSwap) {
		this.timeSwap = timeSwap;
	}
	
	public long getTimeInMillis() {
		return timeInMillis;
	}
	
	public void toggleStartTimeSet() {
		startTimeSet = !startTimeSet;
	}

}
