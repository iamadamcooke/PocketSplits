package com.pocketsplits;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

public class Stopwatch implements Runnable{

	private long startTime;
	private long timeInMillis = 0L;
	private long timeSwap = 0L;
	private long totalTime = 0L;
	private boolean startTimeSet = false;
	private String currentTime = "00:00:00";
	private TextView totalTimeText;
	private Handler handler;
	
	public Stopwatch(Handler handler, TextView totalTimeText) {
		this.handler = handler;
		this.totalTimeText = totalTimeText;
		
		
	}
	
	@Override
	public void run() {
		if(!startTimeSet) {
			startTime = SystemClock.uptimeMillis();
			startTimeSet = true;
		}
		timeInMillis = SystemClock.uptimeMillis() - startTime;
		totalTime = timeSwap + timeInMillis;

		
		totalTimeText.setText(TimeHelpers.millisToStringTime(totalTime));
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
	
	public long getTotalTime() {
		return totalTime;
	}
	
	public void toggleStartTimeSet() {
		startTimeSet = !startTimeSet;
	}

	

}
